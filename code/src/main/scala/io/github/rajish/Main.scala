package io.github.rajish

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.actor._
import akka.stream._
import akka.stream.scaladsl._
import akka.event._

import scalaz.Scalaz._
import scalaz.{\/, -\/, \/-}

object Example {
  implicit val system = ActorSystem("PSUG")
  implicit val materializer = ActorMaterializer()
  implicit val log = Logging(system, this.getClass)

  val example = Flow.fromGraph(GraphDSL.create() { implicit builder =>
    import GraphDSL.Implicits._
    val MAX_THREADS = 4

    // prepare graph elements
    val broadcast = builder.add(Broadcast[Int](2).named("broadcast"))
    val buffer = Flow[Int].buffer(2, OverflowStrategy.backpressure)
    val flow1 = Flow[Int].mapAsync(MAX_THREADS)(i => Future(i.toString)).log("flow1")
    val flow2 = Flow[Int].map{ i =>
      if(i % 2 != 0)
        \/.fromTryCatchThrowable[String, Throwable](throw new Exception(s"$i is odd"))
      else
        \/.fromTryCatchThrowable[String, Throwable](i.toString)
    }.log("flow2")
    val zip = builder.add(ZipWith {(s1: String, s2: \/[Throwable, String]) => (s1, s2)}.named("zip"))

    // define the graph
    broadcast ~> buffer ~> flow1 ~> zip.in0
    broadcast ~>           flow2 ~> zip.in1

    // expose ports
    FlowShape(broadcast.in, zip.out)
  })

  val filter = Flow[(String, \/[Throwable, String])].filter {
    case (s1, \/-(s2)) => true
    case _ => false
  }
  val source  = Source(1 to 10).log("source")
  val sink = Sink.foreach[(String, \/[Throwable, String])] {
    case (s1, \/-(s2)) => log.info(s"[sink] ($s1, $s2)")
    case x => log.error(s"error in input: $x")
  }
}

object Main extends App {
  import Example._
  val decider: Supervision.Decider = {
    case e: Exception =>
      log.error(s"Exception was caught, flow was restarted. Error: '${e.getMessage}'")
      Supervision.Restart
  }


  example
    .withAttributes(ActorAttributes.supervisionStrategy(decider))
    .via(filter)
    .runWith(source, sink)
    ._2.map(p => log.info(p.toString))
}

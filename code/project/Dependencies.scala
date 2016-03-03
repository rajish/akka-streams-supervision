object Dependencies {
  import sbt._
  import Keys._

  /**
   * Contains all Scala dependencies
   */
  object scala {
    val scala_version = scalaVersion.toString

    val reflection = "org.scala-lang" %% "scala-reflect" % scala_version
  }

  /**
   * Contains all Akka dependencies
   */
  object akka {
    private val version = "2.4.2-RC1"

    val actor             = "com.typesafe.akka" %% "akka-actor"                 % version
    val agent             = "com.typesafe.akka" %% "akka-agent"                 % version
    val camel             = "com.typesafe.akka" %% "akka-camel"                 % version
    val cluster           = "com.typesafe.akka" %% "akka-cluster"               % version
    val cluster_sharding  = "com.typesafe.akka" %% "akka-cluster-sharding"      % version
    val osgi              = "com.typesafe.akka" %% "akka-osgi"                  % version
    val osgi_aries        = "com.typesafe.akka" %% "akka-osgi-aries"            % version
    val remote            = "com.typesafe.akka" %% "akka-remote"                % version
    val stream            = "com.typesafe.akka" %% "akka-stream"                % version
    val persist           = "com.typesafe.akka" %% "akka-persistence"           % version
    val persist_cassandra = "com.typesafe.akka" %% "akka-persistence-cassandra" % "0.9" intransitive()
    val contrib           = "com.typesafe.akka" %% "akka-contrib"               % version intransitive()
    val testkit           = "com.typesafe.akka" %% "akka-testkit"               % version
    val slf4j             = "com.typesafe.akka" %% "akka-slf4j"                 % version

    object http {
      val core       = "com.typesafe.akka" %% "akka-http-core"       % version
      val hl         = "com.typesafe.akka" %% "akka-http"            % version
      val testkit    = "com.typesafe.akka" %% "akka-http-testkit"    % version
      val spray_json = "com.typesafe.akka" %% "akka-http-spray-json" % version
      val xml        = "com.typesafe.akka" %% "akka-http-xml"        % version
    }
  }

  /**
   * All Spray dependencies
   */
  object spray {
    private val version = "1.3.3"

    val can     = "io.spray"  %% "spray-can"     % version
    val http    = "io.spray"  %% "spray-http"    % version
    val httpx   = "io.spray"  %% "spray-httpx"   % version
    val routing = "io.spray"  %% "spray-routing" % version
    val client  = "io.spray"  %% "spray-client"  % version
    val testkit = "io.spray"  %% "spray-testkit" % version

    val json    = "io.spray"   %% "spray-json"    % "1.3.2"
    val json4s  = "org.json4s" %% "json4s-native" % "3.3.0"
  }

  /**
   * Apache Commons dependencies
   */
  object commons {
    val codec = "commons-codec"      % "commons-codec"  % "1.9"
    val io =    "commons-io"         % "commons-io"     % "2.4"
    val lang3 = "org.apache.commons" % "commons-lang3"  % "3.3.2"

  }

  /**
    * Scalaz dependencies
    */
  object scalaz {
    private val version = "7.2.0"
    val core     = "org.scalaz" %% "scalaz-core"     % version
    val effect   = "org.scalaz" %% "scalaz-effect"   % version
    val iteratee = "org.scalaz" %% "scalaz-iteratee" % version
  }

  /**
    * Test frameworks
    */
  object testing {
    val specs2     = "org.specs2"     %% "specs2"     % "3.7" // exclude("org.scalaz","scalaz-stream")
    val scalacheck = "org.scalacheck" %% "scalacheck" % "1.12.5"
  }

  /**
    * Cassandra driver et. al.
    */
  object cassandra {
    val driver_core     = "com.datastax.cassandra"  %  "cassandra-driver-core"     % "3.0.0"
    val spark_connector = "com.datastax.spark"      %% "spark-cassandra-connector" % "1.5.0-RC1"
    val snappy_java     = "org.xerial.snappy"       % "snappy-java"                % "1.1.1.3"
    val cassandraunit   = "org.cassandraunit"       % "cassandra-unit"             % "2.2.2.1" excludeAll (
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("org.slf4j", "slf4j-jdk14")
    )
  }

  /**
    * phantom Cassandra DSL
    */
  object phantom {
    private val version = "1.21.0"

    val dsl             = "com.websudos" %% "phantom-dsl"             % version
    val connectors      = "com.websudos" %% "phantom-connectors"      % version
    val thrift          = "com.websudos" %% "phantom-thrift"          % version
    val zookeeper       = "com.websudos" %% "phantom-zookeeper"       % version
    val udt             = "com.websudos" %% "phantom-udt"             % version
    val reactivestreams = "com.websudos" %% "phantom-reactivestreams" % version
    val test            = "com.websudos" %% "phantom-test"            % version excludeAll (
      ExclusionRule("org.slf4j", "slf4j-log4j12"),
      ExclusionRule("org.slf4j", "slf4j-jdk14")
    )
    val util_testing = "com.websudos" %% "util-testing" % "0.5.0"
  }

  object amqp {
    val client   = "com.github.sstone"     %% "amqp-client"     % "1.4"
    val reactive = "io.scalac"             %% "reactive-rabbit" % "1.0.3"
    val akka     = "com.thenewmotion.akka" %% "akka-rabbitmq"   % "2.2"
  }

  val jodatime         = "joda-time"            % "joda-time"           % "2.4"
  val jodaconvert      = "org.joda"             % "joda-convert"        % "1.7"
  val logback          = "ch.qos.logback"       % "logback-classic"     % "1.1.3"
  val newzlyUtil       =  "com.newzly"         %% "util-testing"        % "0.1.19" % "provided"
}

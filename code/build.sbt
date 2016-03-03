import Dependencies._

scalaVersion := "2.11.7"

name := "akka-streams-supervision-example"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(akka.stream, akka.slf4j, logback, scalaz.core)

mainClass in Compile := Some("io.github.rajish.Main")

shellPrompt := { st => Project.extract(st).currentProject.id + "> " }

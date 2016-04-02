name := """scala-play-ws-api-sample"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.3",
  "com.typesafe.akka" %% "akka-http-core" % "2.4.3",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.3",
  "com.typesafe.play" %% "play-ws" % "2.5.0",
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.3" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.3" % "test",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test")


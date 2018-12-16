name := "play-scala-streaming-example"

version := "2.6.x"

scalaVersion := "2.12.7"

crossScalaVersions := Seq("2.11.12", "2.12.4")

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += guice

libraryDependencies += ws % Test
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.19"
libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"
libraryDependencies += "com.typesafe.play" %% "play-iteratees-reactive-streams" % "2.6.1"

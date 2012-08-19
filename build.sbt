organization := "ch.unige"

name := "hotloop"

version := "0.0.1"

scalaVersion := "2.10.0-M6"


//libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.2" % "test"

//libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.1"

libraryDependencies +=   "org.scala-lang" % "scala-reflect" % "2.10.0-M6"

scalacOptions ++= Seq(
      "-Yinline-warnings",
      "-deprecation",
      "-optimize",
      "-unchecked",
      "-language:experimental.macros"
    )


scalaSource in Compile <<= baseDirectory(_ / "src")

scalaSource in Test <<= baseDirectory(_ / "test")


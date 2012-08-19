import sbt._
import Keys._

object HotloopBuild extends Build {
   
  val commonSettings = Seq(
    scalaVersion := "2.10.0-M6",
    resolvers ++= Seq(
      "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
    ),
    scalacOptions ++= Seq(
      "-Yinline-warnings",
      "-deprecation",
      "-optimize",
      "-unchecked",
      "-language:experimental.macros"
    )
  )
  
  lazy val root = Project(
    id = "hotloop",
    base = file("."),
    settings = Project.defaultSettings ++ commonSettings ++ Seq(
      organization := "ch.unige",
      name := "hotloop",
      version := "0.0.1"
    )
  ) dependsOn macroProject

  lazy val macroProject = Project(
    id = "macro",
    base = file("macro"),
    settings = Project.defaultSettings ++ commonSettings ++ Seq(
      libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)
    )
  )
     
}

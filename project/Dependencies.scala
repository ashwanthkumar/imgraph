import sbt._

object Dependencies {
  private val akkaV = "2.3.6"
  val akkaRemote = "com.typesafe.akka" %% "akka-remote" % akkaV
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaV
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaV % "test"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.2.0" % "test"

  val coreDependencies = Seq(scalaTest)
  val oldDependencies = Seq(akkaRemote, akkaActor, akkaTestkit, scalaTest)
}
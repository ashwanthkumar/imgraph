import java.net.InetAddress

import Dependencies._
import sbt.Keys._
import sbt.Package.ManifestAttributes
import sbt.SbtExclusionRule

val scalaV = "2.11.8"
val oldScalaV = "2.10.6"

def appVersion() = sys.env.getOrElse("GO_PIPELINE_LABEL", "1.0.0-SNAPSHOT")

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

lazy val imgraphCore = (project in file("imgraph-core")).
  settings(
    name := "imgraph-core",
    libraryDependencies ++= coreDependencies
  )
  .settings(projectSettings: _*)
  .settings(publishSettings: _*)

@deprecated("Keeping the old code for historical significance")
lazy val imgraphOld = (project in file("imgraph-old"))
  .settings(projectSettings: _*)
  .settings(
    name := "imgraph-old",
    libraryDependencies ++= oldDependencies,
    scalaVersion := oldScalaV
  )

lazy val projectSettings = net.virtualvoid.sbt.graph.Plugin.graphSettings ++ Seq(
  version := appVersion,
  organization := "in.ashwanthkumar",
  scalaVersion := scalaV,
  resolvers += Resolver.mavenLocal,
  resolvers += "Local Maven Repository" at "file://" + Path.userHome.absolutePath + "/.m2/repository",
  resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  excludeDependencies ++= Seq(
    SbtExclusionRule("cglib", "cglib-nodep"),
    SbtExclusionRule("commons-beanutils", "commons-beanutils"),
    SbtExclusionRule("commons-beanutils", "commons-beanutils-core")
  ),
  parallelExecution in ThisBuild := false,
  scalacOptions ++= Seq("-unchecked", "-feature"
    // , "-Ylog-classpath" // useful while debugging dependency classpath issues
  )
)

lazy val _pomExtra =
  <description>imGraph - a distributed graph store</description>
    <url>https://github.com/ashwanthkumar/imgraph</url>
    <licenses>
      <license>
        <name>Apache2</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      </license>
    </licenses>
    <scm>
      <url>https://github.com/ashwanthkumar/imgraph</url>
      <connection>scm:git:git@github.com:ashwanthkumar/imgraph.git</connection>
    </scm>
    <developers>
      <developer>
        <email>ashwanthkumar@googlemail.com</email>
        <name>Ashwanth Kumar</name>
        <url>https://ashwanthkumar.in/</url>
        <id>ashwanthkumar</id>
      </developer>
    </developers>

lazy val publishSettings = Seq(
  publishArtifact := true,
  packageOptions := Seq(ManifestAttributes(
    ("Built-By", InetAddress.getLocalHost.getHostName)
  )),
  crossScalaVersions := Seq(scalaV),
  publishMavenStyle := true,
  // disable publishing test jars
  publishArtifact in Test := false,
  // disable publishing the main docs jar
  publishArtifact in(Compile, packageDoc) := false,
  // disable publishing the main sources jar
  publishArtifact in(Compile, packageSrc) := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (appVersion().trim.endsWith("SNAPSHOT"))
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := _pomExtra
)

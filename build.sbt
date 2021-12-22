name := "scala_play_test"

version := "1.0"

lazy val `scala_play_test` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(jdbc, ehcache, ws, specs2 % Test, guice)
libraryDependencies ++= Seq("org.reactivemongo" %% "reactivemongo" % "1.0.6")
libraryDependencies ++= Seq("com.dripower" %% "play-circe" % "2812.0")
libraryDependencies ++= Seq("io.circe" %% "circe-config" % "0.8.0")

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-generic-extras",
  "io.circe" %% "circe-parser"
).map(_ % "0.13.0")

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)


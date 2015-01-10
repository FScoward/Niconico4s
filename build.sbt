name := "Niconico4s"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "2.4.15" % "test",
  "com.typesafe" % "config" % "1.2.1",
  "org.json4s" %% "json4s-jackson" % "3.2.11",
  "io.argonaut" %% "argonaut" % "6.1-M4" changing(),
  "io.spray" %%  "spray-json" % "1.3.1"
)

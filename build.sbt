name := "catalog_crawler"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.12.1",
  "com.typesafe.akka" %% "akka-actor" % "2.5.26",
  "com.sun.mail" % "jakarta.mail" % "1.6.4",

  "ch.qos.logback"  %  "logback-classic"    % "1.2.3",

  "org.apache.spark" %% "spark-core" % "2.4.4",
  "org.apache.spark" %% "spark-sql" % "2.4.4",
  "org.postgresql" % "postgresql" % "42.2.1",

  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scalamock" %% "scalamock" % "4.1.0",
)
enablePlugins(JavaAppPackaging)

herokuFatJar in Compile := Some((assemblyOutputPath in assembly).value)

enablePlugins(AkkaAppPackaging)

name := "infopage-server"

organization := "ch.fliegen"
version      := "0.1.0"
scalaVersion := "2.11.6"

mainClass in Compile := Some("infopage.ContestApp")

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-actor"     % "2.3.9",
  "com.typesafe.akka"  %% "akka-kernel"    % "2.3.9",
  "io.spray"           %% "spray-can"      % "1.3.2",
  "io.spray"           %% "spray-client"   % "1.3.2",
  "io.spray"           %% "spray-routing"  % "1.3.2",
  "io.spray"           %% "spray-http"     % "1.3.2",
  "io.spray"           %% "spray-httpx"    % "1.3.2",
  "io.spray"           %% "spray-json"     % "1.3.1",
  "com.novus"          %% "salat"          % "1.9.9")

libraryDependencies += "com.lihaoyi" % "ammonite-repl" % "0.2.8" % "test" cross CrossVersion.full
initialCommands in (Test, console) := "ammonite.repl.Repl.main(null)"

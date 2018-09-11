scalacOptions ++= ScalacOptions.tpolecat

libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-blaze-client" % "0.18.17",
    "org.jsoup" % "jsoup" % "1.11.3"
)

scalafmtOnCompile := true

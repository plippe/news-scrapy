scalaVersion := "2.12.7"

scalafmtOnCompile := true

lazy val app = project
    .dependsOn(core)

lazy val core = project
    .settings(
        libraryDependencies ++= Seq(
            "com.amazonaws" % "aws-java-sdk-s3" % "1.11.408",
            "org.http4s" %% "http4s-blaze-client" % "0.18.17",
            "org.jsoup" % "jsoup" % "1.11.3"
        ),

        addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
    )

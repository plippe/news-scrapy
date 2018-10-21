
lazy val commonSettings = Seq(
    scalaVersion := "2.12.7",
    scalafmtOnCompile := true,

    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

lazy val core = project
    .settings(commonSettings)
    .settings(
        libraryDependencies ++= Seq(
            "com.amazonaws" % "aws-java-sdk-s3" % "1.11.427",
            "com.amazonaws" % "aws-java-sdk-lambda" % "1.11.427",
            "org.http4s" %% "http4s-blaze-client" % "0.19.0",
            "org.jsoup" % "jsoup" % "1.11.3",
        ),

        addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")
    )

lazy val app = project
    .settings(commonSettings)
    .dependsOn(core)

lazy val lambda = project
    .settings(commonSettings)
    .dependsOn(core)
    .settings(
        libraryDependencies ++= Seq(
            "com.amazonaws" % "aws-lambda-java-core" % "1.2.0",
            "io.circe" %% "circe-generic" % "0.9.3",
            "io.circe" %% "circe-parser" % "0.9.3"
        )
    )
    .enablePlugins(AwsLambdaPlugin)
    .settings(awsLambdaFunctionName := "news-scrapy")

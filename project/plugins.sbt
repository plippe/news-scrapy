addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")
addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.4")

resolvers += Resolver.url("plippe-sbt", url("http://dl.bintray.com/plippe/sbt"))(Resolver.ivyStylePatterns)
addSbtPlugin("com.github.plippe" % "sbt-aws-lambda" % "0.0.1-4-ga6374e1")

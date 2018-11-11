package com.github.plippe.news.scrapy

import java.io.File
import org.http4s.Uri
import org.scalacheck.{Arbitrary, Gen => OfficialGen}
import scala.util.Properties

import com.github.plippe.news.scrapy.models.AwsS3Uri

object Gen {

  implicit val arbitraryUriGen = Arbitrary(uriGen)
  lazy val uriGen: OfficialGen[Uri] = for {
    scheme <- OfficialGen.oneOf("http", "https")
    domainSegmentsCount <- OfficialGen.choose(1, 3)
    domainSegments <- OfficialGen.listOfN(domainSegmentsCount,
                                          OfficialGen.identifier)
    topLevelDomain <- OfficialGen.oneOf("ie",
                                        "com",
                                        "net",
                                        "org",
                                        "info",
                                        "biz",
                                        "eu",
                                        "co.uk")
    pathsCount <- OfficialGen.choose(0, 10)
    paths <- OfficialGen.listOfN(pathsCount, OfficialGen.identifier)
  } yield
    Uri.unsafeFromString(s"""${scheme}://${domainSegments
      .mkString(".")}.${topLevelDomain}/${paths.mkString("/")}""")

  implicit val arbitraryAwsS3UriGen = Arbitrary(awsS3UriGen)
  lazy val awsS3UriGen: OfficialGen[AwsS3Uri] = for {
    bucket <- OfficialGen.identifier
    keyPathsCount <- OfficialGen.choose(1, 10)
    keyPaths <- OfficialGen.listOfN(keyPathsCount, OfficialGen.identifier)
  } yield AwsS3Uri(bucket, keyPaths.mkString("/"))

  implicit val arbitraryFileGen = Arbitrary(fileGen)
  lazy val fileGen: OfficialGen[File] = for {
    pathsCount <- OfficialGen.choose(1, 10)
    paths <- OfficialGen.listOfN(pathsCount, OfficialGen.identifier)
  } yield {
    val temporaryDirectory = Properties.envOrElse("java.io.tmpdir", "/tmp/")
    new File(s"""${temporaryDirectory}${paths.mkString("/")}""")
  }
}

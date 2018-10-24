package com.github.plippe.news.scrapy

import com.amazonaws.services.s3.AmazonS3URI
import org.http4s.Uri
import org.scalacheck.{Arbitrary, Gen => OfficialGen}

object Gen {

  implicit val arbitraryUriGen = Arbitrary(uriGen)
  lazy val uriGen: OfficialGen[Uri] = for {
    transferProtocol <- OfficialGen.oneOf("http", "https")
    label <- OfficialGen.alphaStr.suchThat(!_.isEmpty)
    topLevelDomain <- OfficialGen.oneOf("ie",
                                        "com",
                                        "net",
                                        "org",
                                        "info",
                                        "biz",
                                        "eu",
                                        "co.uk")
    path <- OfficialGen.alphaStr
  } yield
    Uri.unsafeFromString(
      s"${transferProtocol}://${label}.${topLevelDomain}/${path}")

  implicit val arbitraryAmazonS3UriGen = Arbitrary(amazonS3UriGen)
  lazy val amazonS3UriGen: OfficialGen[AmazonS3URI] = for {
    bucket <- OfficialGen.alphaStr.suchThat(!_.isEmpty)
    key <- OfficialGen.alphaStr.suchThat(!_.isEmpty)
  } yield new AmazonS3URI(s"s3://${bucket}/${key}")

}

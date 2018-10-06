package com.github.plippe.news.scrapy

import com.amazonaws.services.s3.AmazonS3URI
import io.circe._
import org.http4s.Uri

package object json {
  implicit val jsonEncodeUri = new Encoder[Uri] {
    def apply(uri: Uri) = Json.fromString(uri.toString)
  }

  implicit val jsonDecodeUri = new Decoder[Uri] {
    final def apply(c: HCursor): Decoder.Result[Uri] =
      c.as[String].flatMap {
        str =>
          Uri.fromString(str)
            .left.map(DecodingFailure.fromThrowable(_, List.empty))
      }
  }

  implicit val jsonEncodeAmazonS3Uri = new Encoder[AmazonS3URI] {
    def apply(uri: AmazonS3URI) = Json.fromString(uri.toString)
  }

  implicit val jsonDecodeAmazonS3Uri = new Decoder[AmazonS3URI] {
    final def apply(c: HCursor): Decoder.Result[AmazonS3URI] =
      c.as[String].map(new AmazonS3URI(_))
  }
}

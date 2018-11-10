package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import org.http4s.Uri
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

trait Parser[F[_], D, A] {
  def parse(document: D): F[A]
}

trait UriListParser[F[_]] extends Parser[F, String, List[Uri]] {
  implicit val F: ApplicativeError[F, Throwable]

  def baseUri: Uri
  def validUri(uri: Uri): Boolean

  def parse(document: String): F[List[Uri]] =
    Jsoup
      .parse(document, baseUri.toString)
      .select("a[href]")
      .eachAttr("abs:href")
      .asScala
      .toList
      .distinct
      .traverse { str =>
        val uri = Uri
          .fromString(str)
          .left
          .map(err => new Throwable(err.sanitized))

        F.fromEither(uri)
      }
      .map(_.filter(validUri))

}

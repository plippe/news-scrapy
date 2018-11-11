package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import org.http4s.Uri
import org.jsoup.Jsoup
import scala.collection.JavaConverters._

import com.github.plippe.news.scrapy.models.WebPage

trait Parser[F[_], A] {
  def parse(webPage: WebPage): F[A]
}

abstract class UriListParser[F[_]: ApplicativeError[?[_], Throwable]]
    extends Parser[F, List[Uri]] {

  def validUri(uri: Uri): Boolean

  def parse(webPage: WebPage): F[List[Uri]] =
    Jsoup
      .parse(webPage.html, webPage.uri.toString)
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

        ApplicativeError[F, Throwable].fromEither(uri)
      }
      .map(_.filter(validUri))

}

package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import fs2.Stream
import org.http4s.Uri
import org.jsoup.Jsoup

import scala.collection.JavaConverters._

class IrishExaminerComArticleListParser[F[_]: ApplicativeError[?[_], Throwable]]()
  extends Parser[F, Uri] {

  val baseUrl = "https://www.irishexaminer.com"

  def parse(content: String): Stream[F, Uri] = {
    val uris: F[List[Uri]] = Jsoup
      .parse(content)
      .select(".additional-block-dashboard a[href]")
      .eachAttr("abs:href")
      .asScala
      .toList
      .map(relativeUrl => baseUrl + relativeUrl)
      .traverse { str =>
        val uri = Uri
          .fromString(str)
          .left
          .map(err => new Throwable(err.sanitized))

        ApplicativeError[F, Throwable].fromEither(uri)
      }

    Stream.eval(uris).flatMap(uris => Stream.apply(uris: _*))
  }

}

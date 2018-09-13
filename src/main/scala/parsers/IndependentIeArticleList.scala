package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import collection.JavaConverters._
import fs2.Stream
import org.http4s.Uri
import org.jsoup.Jsoup

class IndependentIeArticleListParser[F[_]: ApplicativeError[?[_], Throwable]]()
    extends Parser[F, Uri] {
  def parse(content: String): Stream[F, Uri] = {
    val uris: F[List[Uri]] = Jsoup
      .parse(content)
      .select("article a")
      .eachAttr("abs:href")
      .asScala
      .toList
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

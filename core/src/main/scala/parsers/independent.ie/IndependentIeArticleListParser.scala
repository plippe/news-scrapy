package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import org.http4s.Uri
import org.jsoup.Jsoup
import scala.collection.JavaConverters._

class IndependentIeArticleListParser[F[_]: ApplicativeError[?[_], Throwable]]()
    extends Parser[F, List[Uri]] {

  def parse(content: String): F[List[Uri]] = {
    Jsoup
      .parse(content)
      .select("article a[href]")
      .eachAttr("href")
      .asScala
      .toList
      .traverse { str =>
        val uri = Uri
          .fromString(str)
          .left
          .map(err => new Throwable(err.sanitized))

        ApplicativeError[F, Throwable].fromEither(uri)
      }
  }

}

package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import cats.implicits._
import collection.JavaConverters._
import fs2.Stream
import org.http4s.client.Client
import org.http4s.{Request, Uri}
import org.jsoup.Jsoup

class IndependentIeArticleListPageParser[F[_]](val client: Client[F])(
    implicit F: ApplicativeError[F, Throwable])
    extends PageParser[F, Uri] {
  def readHtml(html: String): Stream[F, Uri] = {
    val uris: F[List[Uri]] = Jsoup
      .parse(html)
      .select("article a")
      .eachAttr("abs:href")
      .asScala
      .toList
      .traverse { str =>
        val uri = Uri
          .fromString(str)
          .left
          .map(err => new Throwable(err.sanitized))

        F.fromEither(uri)
      }

    Stream.eval(uris).flatMap(uris => Stream.apply(uris: _*))
  }
}

object IndependentIeArticleListPageParser {
  def request[F[_]] =
    Request[F](uri = Uri.uri("https://www.independent.ie/news/"))

  def parseWith[F[_]](client: Client[F])(
      implicit F: ApplicativeError[F, Throwable]): Stream[F, Uri] = {
    val parser = new IndependentIeArticleListPageParser(client)
    parser.parse(request)
  }
}

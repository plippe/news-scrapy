package com.github.plippe.news.scrapy.pages

import cats.ApplicativeError
import cats.implicits._
import collection.JavaConverters._
import fs2.Stream
import org.http4s.client.Client
import org.http4s.{Request, Uri}
import org.jsoup.Jsoup

class IndependentIeArticleListParser[F[_]: ApplicativeError[?[_], Throwable]]
    extends Parser[F, Uri] {
  def parse(html: String): Stream[F, Uri] = {
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

        ApplicativeError[F, Throwable].fromEither(uri)
      }

    Stream.eval(uris).flatMap(uris => Stream.apply(uris: _*))
  }
}

class IndependentIeArticleList[F[_]](fetcher: Fetcher[F],
                                     parser: Parser[F, Uri]) {

  def request = Request[F](uri = Uri.uri("https://www.independent.ie/news/"))

  def stream(): Stream[F, Uri] =
    for {
      html <- fetcher.fetch(request)
      uris <- parser.parse(html)
    } yield uris

}

object IndependentIeArticleList {
  def stream[F[_]: ApplicativeError[?[_], Throwable]](client: Client[F]) = {
    val fetcher = new DefaultFetcher[F](client)
    val parser = new IndependentIeArticleListParser[F]
    val obj = new IndependentIeArticleList(fetcher, parser)

    obj.stream
  }
}

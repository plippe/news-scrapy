package com.github.plippe.news.scrapy

import cats._
import cats.effect._
import cats.implicits._
import collection.JavaConverters._
import fs2._
import fs2.StreamApp.ExitCode
import org.http4s.client.blaze._
import org.http4s.client.Client
import org.http4s.{EntityDecoder, Uri}
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

case class Article(content: String)

case class IndependentIe[F[_]](client: Client[F])(implicit F: MonadError[F, Throwable], ED: EntityDecoder[F, String]) {
    def parse(uri: Uri): F[Iterable[Article]] = for {
        html <- client.expect[String](uri)
        uris <- parseArticleList(html)
        htmls <- uris.traverse(client.expect[String])
        articles <- htmls.traverse(parseArticlePage)
    } yield articles

    def parseArticleList(html: String): F[List[Uri]] = F.catchNonFatal {
        Jsoup.parse(html)
            .select("article a")
            .eachAttr("href")
            .asScala.toList
            .map(Uri.unsafeFromString)
    }

    def parseArticlePage(html: String): F[Article] = F.catchNonFatal {
        val document = Jsoup.parse(html)
        val content = document.select("article h1").outerHtml() +
            document.select("article .body").outerHtml()

        Article(content)
    }
}

object IndependentIe {
    def parseArticleList[F[_]](client: Client[F])(implicit F: MonadError[F, Throwable], ED: EntityDecoder[F, String]) = {
        val uri = Uri.uri("https://www.independent.ie/news/")
        new IndependentIe[F](client).parse(uri)
    }
}

object Main extends StreamApp[IO] {
    def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] = {
        for {
            client <- Http1Client.stream[IO]()
            articles <- Stream.eval(IndependentIe.parseArticleList(client))
            _ = println(articles)
        } yield ExitCode.Success
    }
}

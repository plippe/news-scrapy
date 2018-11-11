package com.github.plippe.news.scrapy

import cats.effect.{ConcurrentEffect, IO}
import cats.implicits._
import fs2.Stream
import java.io.File
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.Uri
import scala.concurrent.ExecutionContext

import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._
import com.github.plippe.news.scrapy.syntax._

object Main extends App {

  def run[F[_]: ConcurrentEffect](): F[Unit] = {
    val stream = for {
      client <- BlazeClientBuilder[F](ec).stream

      articleListParser = new IndependentIeArticleListParser[F]()
      articleParser = new IndependentIeArticleParser[F]()

      webPageReader: WebPageReader[F] = new WebPageHttpStore[F](client)
      webPageWriter: WebPageWriter[F] = new WebPageHardDriveStore[F](
        new File("."))

      articleListUri = Uri.uri("https://www.independent.ie")

      _ = println(s"Read ${articleListUri} from HTTP")
      articleListWebPage <- webPageReader.read(articleListUri).stream

      _ = println(s"Write ${articleListUri} to Amazon S3")
      _ <- webPageWriter.write(articleListWebPage).stream

      _ = println(s"Parse ${articleListUri} for URIs")
      articleUri <- articleListParser
        .parse(articleListWebPage.html)
        .stream
        .flatMap { uris =>
          Stream.apply(uris: _*)
        }

      _ = println(s"Read ${articleUri} from HTTP")
      articleWebPage <- webPageReader.read(articleUri).stream

      _ = println(s"Write ${articleUri} to Amazon S3")
      _ <- webPageWriter.write(articleWebPage).stream

      _ = println(s"Parse ${articleUri}")
      article <- articleParser.parse(articleWebPage.html).stream

      _ = println(s"Done ${article}")
    } yield ()

    stream.compile.drain.as(())
  }

  implicit val ec = ExecutionContext.global
  implicit val cs = IO.contextShift(ec)
  run[IO]().unsafeRunSync()
}

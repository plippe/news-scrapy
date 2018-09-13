package com.github.plippe.news.scrapy

import cats.effect.IO
import cats.implicits._
import org.http4s.client.blaze.Http1Client
import org.http4s.Uri

import com.github.plippe.news.scrapy.models.Source
import com.github.plippe.news.scrapy.fetchers.HttpFetcher
import com.github.plippe.news.scrapy.parsers.IndependentIeArticleListParser

object Main extends App { // IOApp {
  trait ExitCode
  object ExitCode {
    case object Success extends ExitCode
  }

  def run(args: List[String]): IO[ExitCode] = {
    val stream = for {
      client <- Http1Client.stream[IO]()

      req = Source.Http(Uri.uri("https://www.independent.ie/news/"))
      document <- new HttpFetcher[IO](client).fetch(req)
      uris <- new IndependentIeArticleListParser[IO]().parse(document)
      _ = println(s"IndependentIeArticleListPageParser - $uris")
    } yield ()

    locally(args)
    stream.compile.drain.as(ExitCode.Success)
  }

  run(args.toList).unsafeRunSync()
}

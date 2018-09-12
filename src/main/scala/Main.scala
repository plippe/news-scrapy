package com.github.plippe.news.scrapy

import cats.effect.IO
import cats.implicits._
import org.http4s.client.blaze.{Http1Client}

import com.github.plippe.news.scrapy.pages._

object Main extends App { // IOApp {
  trait ExitCode
  object ExitCode {
    case object Success extends ExitCode
  }

  def run(args: List[String]): IO[ExitCode] = {
    val stream = for {
      client <- Http1Client.stream[IO]()

      independentIeArticlesUri <- IndependentIeArticleList.stream[IO](client)
      _ = println(
        s"IndependentIeArticleListPageParser - $independentIeArticlesUri")
    } yield ()

    locally(args)
    stream.compile.drain.as(ExitCode.Success)
  }

  run(args.toList).unsafeRunSync()
}

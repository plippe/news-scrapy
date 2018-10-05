package com.github.plippe.news.scrapy

import cats.effect.{IO, Effect}
import cats.implicits._
import com.amazonaws.services.s3.{AmazonS3ClientBuilder, AmazonS3URI}
import org.http4s.client.blaze.Http1Client
import org.http4s.Uri

import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._

object Main extends App { // IOApp {
  trait ExitCode
  object ExitCode {
    case object Success extends ExitCode
  }

  def run[F[_]: Effect](): F[ExitCode] = {
    val stream = for {
      client <- Http1Client.stream[F]()
      httpStore = new HttpStore[F](client)
      amazonS3Store = new AmazonS3Store[F](
        AmazonS3ClientBuilder.defaultClient())

      _ = println("Read independent.ie from HTTP")
      articleListSourceHttpUri = Uri.uri("https://www.independent.ie/news/")
      articleListSourceHttpLink = Link.Http(articleListSourceHttpUri)
      articleListSourceHttp <- httpStore.read(articleListSourceHttpLink)

      _ = println("Write independent.ie to Amazon S3")
      articleListAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie/news/index.html")
      articleListAmazonS3Link = Link.AmazonS3(articleListAmazonS3Uri)
      _ <- amazonS3Store.write(articleListAmazonS3Link, articleListSourceHttp)

      _ = println("Read independent.ie from Amazon S3")
      articleList <- amazonS3Store.read(articleListAmazonS3Link)

      _ = println("Parse independent.ie for URIs")
      articleUri <- new IndependentIeArticleListParser[F]().parse(articleList)
      articleLink = Link.Http(articleUri)

      _ = println(s"Read ${articleUri} from HTTP")
      article <- httpStore.read(articleLink)

      _ = println(s"Write ${articleUri} to Amazon S3")
      articleAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie${articleUri.path}")
      articleAmazonS3Link = Link.AmazonS3(articleAmazonS3Uri)
      _ <- amazonS3Store.write(articleAmazonS3Link, article)

      _ = println(s"Parse ${articleUri}")
      document <- new IndependentIeArticleParser[F]().parse(article)

      _ = println(s"Done ${articleUri}")
    } yield ()

    stream.compile.drain.as(ExitCode.Success)
  }

  run[IO]().unsafeRunSync()
}

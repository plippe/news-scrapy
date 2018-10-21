package com.github.plippe.news.scrapy

import cats.effect.{ConcurrentEffect, IO}
import cats.implicits._
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.Uri
import scala.concurrent.ExecutionContext

import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._
import com.github.plippe.news.scrapy.syntax._

object Main extends App {

  def run[F[_]: ConcurrentEffect](): F[Unit] = {
    val stream = for {
      client <- BlazeClientBuilder[F](ec).stream
      httpStore = new HttpStore[F](client)
      amazonS3Store = new AmazonS3Store[F](
        AmazonS3ClientBuilder.defaultClient())

      hardDriveStore = new HardDriveStore[F]

      newsUrl = "https://www.irishexaminer.com/"
      directory = "irishexaminer"
      rootFile = "news.html"

      _ = println("Read from HTTP")
      articleListSourceHttpUri = Uri.uri("https://www.irishexaminer.com/")
      articleListSourceHttpLink = Link.Http(articleListSourceHttpUri)
      articleListSourceHttp <- httpStore.read(articleListSourceHttpLink).stream

      _ = println(s"Write to Hard Drive - $newsUrl")
      /*articleListAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie/news/index.html")*/
      articleListHardDriveLink = Link.HardDrive(s"$directory/$rootFile")
      _ <- hardDriveStore.write(articleListHardDriveLink, articleListSourceHttp).stream

      _ = println(s"Read from Hard Drive - $directory/$rootFile")
      articleList <- hardDriveStore.read(articleListHardDriveLink).stream

      _ = println(s"Parse $newsUrl for URIs")
      articleUri <- new IrishExaminerComArticleListParser[F]()
        .parse(articleList)
        .stream
        .flatMap { uris => fs2.Stream.apply(uris: _*) }

      articleLink = Link.Http(articleUri)

      _ = println(s"Read ${articleUri} from HTTP")
      article <- httpStore.read(articleLink).stream

      _ = println(s"Write ${articleUri} to Amazon S3")
      articleHardDriveLink = Link.HardDrive(s"$directory/${articleUri.path}")
      _ <- hardDriveStore.write(articleHardDriveLink, article).stream

      _ = println(s"Parse ${articleUri}")
      document <- new IrishExaminerComArticleParser[F]().parse(article).stream

      _ = println(s"Done ${document}")

      _ = println(s"Done ${articleUri}")
    } yield ()

    stream.compile.drain.as(())
  }

  implicit val ec = ExecutionContext.global
  implicit val cs = IO.contextShift(ec)
  run[IO]().unsafeRunSync()
}

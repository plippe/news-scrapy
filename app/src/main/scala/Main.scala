package com.github.plippe.news.scrapy

import cats.effect.{Effect, IO}
import cats.implicits._
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.http4s.client.blaze.Http1Client
import org.http4s.Uri
import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._

object Main extends App {
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

      hardDriveStore = new HardDriveStore[F]

      newsUrl = "https://www.irishexaminer.com/"
      directory = "irishexaminer"
      rootFile = "news.html"

      _ = println("Read from HTTP")
      articleListSourceHttpUri = Uri.uri("https://www.irishexaminer.com/")
      articleListSourceHttpLink = Link.Http(articleListSourceHttpUri)
      articleListSourceHttp <- httpStore.read(articleListSourceHttpLink)

      _ = println(s"Write to Hard Drive - $newsUrl")
      /*articleListAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie/news/index.html")*/
      articleListHardDriveLink = Link.HardDrive(s"$directory/$rootFile")
      _ <- hardDriveStore.write(articleListHardDriveLink, articleListSourceHttp)

      _ = println(s"Read from Hard Drive - $directory/$rootFile")
      articleList <- hardDriveStore.read(articleListHardDriveLink)

      _ = println(s"Parse $newsUrl for URIs")
      articleUri <- new IrishExaminerComArticleListParser[F]().parse(articleList)
      articleLink = Link.Http(articleUri)

      _ = println(s"Read ${articleUri} from HTTP")
      article <- httpStore.read(articleLink)

      _ = println(s"Write ${articleUri} to Amazon S3")
      articleHardDriveLink = Link.HardDrive(s"$directory/${articleUri.path}")
      _ <- hardDriveStore.write(articleHardDriveLink, article)

      _ = println(s"Parse ${articleUri}")
      document <- new IrishExaminerComArticleParser[F]().parse(article)

      _ = println(s"Done ${document}")

      _ = println(s"Done ${articleUri}")
    } yield ()

    stream.compile.drain.as(ExitCode.Success)
  }

  run[IO]().unsafeRunSync()
}

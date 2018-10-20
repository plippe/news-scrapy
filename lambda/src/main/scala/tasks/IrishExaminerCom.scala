package com.github.plippe.news.scrapy.tasks

import cats.effect.ConcurrentEffect
import com.amazonaws.services.s3.{AmazonS3ClientBuilder, AmazonS3URI}
import fs2.Stream
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.Uri
import scala.concurrent.ExecutionContext

import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._

object IrishExaminerCom {

  def stream[F[_]: ConcurrentEffect]()(implicit ec: ExecutionContext): Stream[F, Unit] = for {
    client <- BlazeClientBuilder[F](ec).stream
    httpStore = new HttpStore[F](client)
    amazonS3Store = new AmazonS3Store[F](
      AmazonS3ClientBuilder.defaultClient())

    _ = println("Read irishexaminer.com from HTTP")
    articleListHttpUri = Uri.uri("https://www.irishexaminer.com")
    articleListHttpLink = Link.Http(articleListHttpUri)
    articleListHttp <- httpStore.read(articleListHttpLink)

    _ = println("Write irishexaminer.com to Amazon S3")
    articleListAmazonS3Uri = new AmazonS3URI(
      s"s3://plippe-us-east-1/irishexaminer.com/news/index.html")
    articleListAmazonS3Link = Link.AmazonS3(articleListAmazonS3Uri)
    _ <- amazonS3Store.write(articleListAmazonS3Link, articleListHttp)

    _ = println("Parse irishexaminer.com for URIs")
    articleHttpUri <- new IrishExaminerComArticleListParser[F]().parse(articleListHttp)
    articleHttpLink = Link.Http(articleHttpUri)

    _ = println(s"Read ${articleHttpUri} from HTTP")
    articleHttp <- httpStore.read(articleHttpLink)

    _ = println(s"Write ${articleHttpUri} to Amazon S3")
    articleAmazonS3Uri = new AmazonS3URI(
      s"s3://plippe-us-east-1/irishexaminer.com${articleHttpUri.path}")
    articleAmazonS3Link = Link.AmazonS3(articleAmazonS3Uri)
    _ <- amazonS3Store.write(articleAmazonS3Link, articleHttp)

    _ = println(s"Parse ${articleHttpUri}")
    document <- new IrishExaminerComArticleParser[F]().parse(articleHttp)

    _ = println(s"Done ${articleHttpUri}")
  } yield ()

}

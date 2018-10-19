package com.github.plippe.news.scrapy.tasks

import cats.effect.Effect
import cats.implicits._
import com.amazonaws.services.s3.{AmazonS3ClientBuilder, AmazonS3URI}
import org.http4s.client.blaze.Http1Client
import org.http4s.Uri

import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._

trait IndependentIe {

  def run[F[_]: Effect](): F[Unit] = {
    val stream = for {
      client <- Http1Client.stream[F]()
      httpStore = new HttpStore[F](client)
      amazonS3Store = new AmazonS3Store[F](
        AmazonS3ClientBuilder.defaultClient())

      _ = println("Read independent.ie from HTTP")
      articleListHttpUri = Uri.uri("https://www.independent.ie/news/")
      articleListHttpLink = Link.Http(articleListHttpUri)
      articleListHttp <- httpStore.read(articleListHttpLink)

      _ = println("Write independent.ie to Amazon S3")
      articleListAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie/news/index.html")
      articleListAmazonS3Link = Link.AmazonS3(articleListAmazonS3Uri)
      _ <- amazonS3Store.write(articleListAmazonS3Link, articleListHttp)

      _ = println("Parse independent.ie for URIs")
      articleHttpUri <- new IndependentIeArticleListParser[F]().parse(articleListHttp)
      articleHttpLink = Link.Http(articleHttpUri)

      _ = println(s"Read ${articleHttpUri} from HTTP")
      articleHttp <- httpStore.read(articleHttpLink)

      _ = println(s"Write ${articleHttpUri} to Amazon S3")
      articleAmazonS3Uri = new AmazonS3URI(
        s"s3://plippe-us-east-1/independent.ie${articleHttpUri.path}")
      articleAmazonS3Link = Link.AmazonS3(articleAmazonS3Uri)
      _ <- amazonS3Store.write(articleAmazonS3Link, articleHttp)

      _ = println(s"Parse ${articleHttpUri}")
      document <- new IndependentIeArticleParser[F]().parse(articleHttp)

      _ = println(s"Done ${articleHttpUri}")
    } yield ()

    stream.compile.drain.as(())
  }

}

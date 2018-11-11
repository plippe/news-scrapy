package com.github.plippe.news.scrapy.tasks

import cats.effect.ConcurrentEffect
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import fs2.Stream
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.Uri
import scala.concurrent.ExecutionContext

import com.github.plippe.news.scrapy.models._
import com.github.plippe.news.scrapy.stores._
import com.github.plippe.news.scrapy.parsers._
import com.github.plippe.news.scrapy.syntax._

object IrishExaminerCom {

  def stream[F[_]: ConcurrentEffect]()(
      implicit ec: ExecutionContext): Stream[F, Unit] =
    for {
      client <- BlazeClientBuilder[F](ec).stream

      articleListParser = new IrishExaminerComArticleListParser[F]()
      articleParser = new IrishExaminerComArticleParser[F]()

      webPageReader: WebPageReader[F] = new WebPageHttpStore[F](client)
      webPageWriter: WebPageWriter[F] = new WebPageAwsS3Store[F](
        AmazonS3ClientBuilder.defaultClient(),
        AwsS3Uri("plippe-us-east-1", "news-scrapy")
      )

      articleListUri = Uri.uri("https://www.irishexaminer.com")

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

}

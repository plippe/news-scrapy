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

abstract class Generic[F[_]: ConcurrentEffect] {

  val articleListUri: Uri
  val articleListParser: UriListParser[F]
  val articleParser: Parser[F, String, String]

  def stream()(implicit ec: ExecutionContext): Stream[F, Unit] =
    for {
      client <- BlazeClientBuilder[F](ec).stream

      webPageReader: WebPageReader[F] = new WebPageHttpStore[F](client)
      webPageWriter: WebPageWriter[F] = new WebPageAwsS3Store[F](
        AmazonS3ClientBuilder.defaultClient(),
        AwsS3Uri("plippe-us-east-1", "news-scrapy")
      )

      _ = println(s"Read ${articleListUri} from HTTP")
      articleListWebPage <- webPageReader.read(articleListUri).stream

      _ = println(s"Write ${articleListUri} to Amazon S3")
      _ <- webPageWriter.write(articleListWebPage).stream

      _ = println(s"Parse ${articleListUri} for URIs")
      articleUri <- articleListParser
        .parse(articleListWebPage)
        .stream
        .flatMap { uris =>
          Stream.apply(uris: _*)
        }

      _ = println(s"Read ${articleUri} from HTTP")
      articleWebPage <- webPageReader.read(articleUri).stream

      _ = println(s"Write ${articleUri} to Amazon S3")
      _ <- webPageWriter.write(articleWebPage).stream

      _ = println(s"Parse ${articleUri}")
      article <- articleParser.parse(articleWebPage).stream

      _ = println(s"Done ${article}")
    } yield ()

}

object IndependentIe {
  def stream[F[_]: ConcurrentEffect]()(implicit ec: ExecutionContext) =
    new Generic[F] {
      val articleListUri = Uri.uri("https://www.independent.ie")
      val articleListParser: UriListParser[F] =
        new IndependentIeArticleListParser[F]()
      val articleParser: Parser[F, String, String] =
        new IndependentIeArticleParser[F]()
    }.stream
}

object IrishExaminerCom {
  def stream[F[_]: ConcurrentEffect]()(implicit ec: ExecutionContext) =
    new Generic[F] {
      val articleListUri = Uri.uri("https://www.irishexaminer.com")
      val articleListParser: UriListParser[F] =
        new IrishExaminerComArticleListParser[F]()
      val articleParser: Parser[F, String, String] =
        new IrishExaminerComArticleParser[F]()
    }.stream
}

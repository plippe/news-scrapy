package com.github.plippe.news.scrapy.stores

import cats.effect.{Effect, Sync}
import cats.implicits._
import com.amazonaws.services.s3.{AmazonS3, AmazonS3URI}
import org.http4s.client.Client
import org.http4s.{Request, Uri}

import com.github.plippe.news.scrapy.models.WebPage

trait WebPageReader[F[_]] { def read(uri: Uri): F[WebPage] }
trait WebPageWriter[F[_]] { def write(webPage: WebPage): F[Unit] }

class WebPageHttpStore[F[_]: Sync](client: Client[F]) extends WebPageReader[F] {

  def read(uri: Uri): F[WebPage] = {
    val req = Request[F](uri = uri)
    client
      .expect[String](req)
      .map(WebPage(uri, _))
  }

}

class WebPageAwsS3Store[F[_]: Effect](client: AmazonS3,
                                      awsS3UriPrefix: AmazonS3URI)
    extends WebPageReader[F]
    with WebPageWriter[F] {

  def awsS3Uri(uri: Uri): AmazonS3URI = {
    new AmazonS3URI(s"${awsS3UriPrefix}/${uri}")
  }

  def read(uri: Uri): F[WebPage] =
    Effect[F].catchNonFatal {
      val s3Uri = awsS3Uri(uri)
      val html = client.getObjectAsString(s3Uri.getBucket, s3Uri.getKey)

      WebPage(uri, html)
    }

  def write(webPage: WebPage): F[Unit] =
    Effect[F].catchNonFatal {
      val s3Uri = awsS3Uri(webPage.uri)
      client.putObject(s3Uri.getBucket, s3Uri.getKey, webPage.html)
      ()
    }

}

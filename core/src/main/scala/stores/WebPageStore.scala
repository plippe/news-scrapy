package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import cats.effect.Sync
import cats.implicits._
import com.amazonaws.services.s3.{AmazonS3, AmazonS3URI}
import com.amazonaws.services.s3.model.ObjectMetadata
import java.io.ByteArrayInputStream
import java.time.ZonedDateTime
import org.http4s.client.Client
import org.http4s.{Request, Uri}

import com.github.plippe.news.scrapy.models.WebPage

trait WebPageReader[F[_]] { def read(uri: Uri): F[WebPage] }
trait WebPageWriter[F[_]] { def write(uri: Uri, webPage: WebPage): F[Unit] }

class WebPageHttpStore[F[_]: Sync](client: Client[F]) extends WebPageReader[F] {

  def read(uri: Uri): F[WebPage] = {
    val req = Request[F](uri = uri)
    client
      .expect[String](req)
      .map(WebPage(_, uri, ZonedDateTime.now))
  }

}

class WebPageAwsS3Store[F[_]: ApplicativeError[?[_], Throwable]](
    client: AmazonS3,
    awsS3UriPrefix: AmazonS3URI)
    extends WebPageReader[F]
    with WebPageWriter[F] {

  def awsS3Uri(uri: Uri): AmazonS3URI = {
    new AmazonS3URI(s"${awsS3UriPrefix}/${uri}")
  }

  def readAtField() = "web-page-read-at"

  def read(uri: Uri): F[WebPage] =
    ApplicativeError[F, Throwable].catchNonFatal {
      val s3Uri = awsS3Uri(uri)
      val html = client.getObjectAsString(s3Uri.getBucket, s3Uri.getKey)
      val readAtStr = client
        .getObjectMetadata(s3Uri.getBucket, s3Uri.getKey)
        .getUserMetaDataOf(readAtField)

      WebPage(html, uri, ZonedDateTime.parse(readAtStr))
    }

  def write(uri: Uri, webPage: WebPage): F[Unit] =
    ApplicativeError[F, Throwable].catchNonFatal {
      val s3Uri = awsS3Uri(uri)
      val s3Input = new ByteArrayInputStream(webPage.html.getBytes)
      val s3Metadata = new ObjectMetadata()
      s3Metadata.addUserMetadata(readAtField(), webPage.readAt.toString)

      client.putObject(s3Uri.getBucket, s3Uri.getKey, s3Input, s3Metadata)
      ()
    }

}

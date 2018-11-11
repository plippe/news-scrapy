package com.github.plippe.news.scrapy.stores

import cats.effect.{Effect, Sync}
import cats.implicits._
import com.amazonaws.services.s3.{AmazonS3, AmazonS3URI}
import java.io.{File, FileWriter}
import org.http4s.client.Client
import org.http4s.{Request, Uri}
import scala.io.Source

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

class WebPageAwsS3Store[F[_]: Effect](client: AmazonS3, prefix: AmazonS3URI)
    extends WebPageReader[F]
    with WebPageWriter[F] {

  def buildAwsS3Uri(prefix: AmazonS3URI, uri: Uri): AmazonS3URI = {
    new AmazonS3URI(s"${prefix}/${uri}")
  }

  def read(uri: Uri): F[WebPage] =
    Effect[F].catchNonFatal {
      val awsS3Uri = buildAwsS3Uri(prefix, uri)
      val html = client.getObjectAsString(awsS3Uri.getBucket, awsS3Uri.getKey)

      WebPage(uri, html)
    }

  def write(webPage: WebPage): F[Unit] =
    Effect[F].catchNonFatal {
      val awsS3Uri = buildAwsS3Uri(prefix, webPage.uri)
      client.putObject(awsS3Uri.getBucket, awsS3Uri.getKey, webPage.html)
      ()
    }

}

class WebPageHardDriveStore[F[_]: Effect](prefix: File)
    extends WebPageReader[F]
    with WebPageWriter[F] {

  def buildFile(prefix: File, uri: Uri): File = {
    new File(prefix, uri.toString)
  }

  def read(uri: Uri): F[WebPage] =
    Effect[F].catchNonFatal {
      val file = buildFile(prefix, uri)
      val html = Source.fromFile(file).getLines.mkString("\n")

      WebPage(uri, html)
    }

  def write(webPage: WebPage): F[Unit] =
    Effect[F].catchNonFatal {
      val file = buildFile(prefix, webPage.uri)

      file.getParentFile().mkdirs()

      val writer = new FileWriter(file)
      writer.write(webPage.html)
      writer.close()
    }

}

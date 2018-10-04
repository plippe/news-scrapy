package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import com.amazonaws.services.s3.AmazonS3Client
import fs2.Stream

import com.github.plippe.news.scrapy.models.Link.AmazonS3

class AmazonS3Store[F[_]](client: AmazonS3Client)(
    implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, AmazonS3]
    with Writer[F, AmazonS3] {

  def read(link: AmazonS3): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      client.getObjectAsString(link.uri.getBucket, link.uri.getKey)
    }
  }

  def write(link: AmazonS3, document: String): Stream[F, AmazonS3] =
    Stream.eval {
      val f = F.catchNonFatal {
        client.putObject(link.uri.getBucket, link.uri.getKey, document)
      }
      F.map(f)(_ => link)
    }

}

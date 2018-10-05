package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import com.amazonaws.services.s3.AmazonS3
import fs2.Stream

import com.github.plippe.news.scrapy.models.Link

class AmazonS3Store[F[_]](client: AmazonS3)(
    implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, Link.AmazonS3]
    with Writer[F, Link.AmazonS3] {

  def read(link: Link.AmazonS3): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      client.getObjectAsString(link.uri.getBucket, link.uri.getKey)
    }
  }

  def write(link: Link.AmazonS3, document: String): Stream[F, Link.AmazonS3] =
    Stream.eval {
      val f = F.catchNonFatal {
        client.putObject(link.uri.getBucket, link.uri.getKey, document)
      }
      F.map(f)(_ => link)
    }

}

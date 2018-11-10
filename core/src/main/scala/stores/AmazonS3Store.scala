package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import cats.implicits._
import com.amazonaws.services.s3.AmazonS3

import com.github.plippe.news.scrapy.models.Link

class AmazonS3Store[F[_]](client: AmazonS3)(
    implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, Link.AmazonS3, String]
    with Writer[F, Link.AmazonS3, String] {

  def read(link: Link.AmazonS3): F[String] =
    F.catchNonFatal {
      client.getObjectAsString(link.uri.getBucket, link.uri.getKey)
    }

  def write(link: Link.AmazonS3, document: String): F[Link.AmazonS3] =
    F.catchNonFatal {
        client.putObject(link.uri.getBucket, link.uri.getKey, document)
      }
      .map(_ => link)

}

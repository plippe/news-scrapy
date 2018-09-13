package com.github.plippe.news.scrapy.fetchers

import cats.ApplicativeError
import com.amazonaws.services.s3.AmazonS3Client
import fs2.Stream

import com.github.plippe.news.scrapy.models.Source.AmazonS3

class AmazonS3Fetcher[F[_]](client: AmazonS3Client)(
    implicit F: ApplicativeError[F, Throwable])
    extends Fetcher[F, AmazonS3] {

  def fetch(event: AmazonS3): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      client.getObjectAsString(event.uri.getBucket, event.uri.getKey)
    }
  }

}

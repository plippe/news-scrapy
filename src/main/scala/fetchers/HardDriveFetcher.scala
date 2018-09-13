package com.github.plippe.news.scrapy.fetchers

import cats.ApplicativeError
import fs2.Stream
import scala.io.Source

import com.github.plippe.news.scrapy.models.Source.HardDrive

class HardDriveFetcher[F[_]](implicit F: ApplicativeError[F, Throwable])
    extends Fetcher[F, HardDrive] {

  def fetch(event: HardDrive): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      Source.fromFile(event.path).getLines.mkString("\n")
    }
  }

}

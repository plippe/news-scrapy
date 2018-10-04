package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import fs2.Stream
import java.io.{File, PrintWriter}
import scala.io.Source

import com.github.plippe.news.scrapy.models.Link.HardDrive

class HardDriveStore[F[_]](implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, HardDrive]
    with Writer[F, HardDrive] {

  def read(link: HardDrive): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      Source.fromFile(link.path).getLines.mkString("\n")
    }
  }

  def write(link: HardDrive, document: String): Stream[F, HardDrive] =
    Stream.eval {
      F.catchNonFatal {
        val writer = new PrintWriter(new File(link.path))
        writer.write(document)
        writer.close()

        link
      }
    }

}

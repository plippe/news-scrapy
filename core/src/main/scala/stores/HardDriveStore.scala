package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import fs2.Stream
import java.io.{File, PrintWriter}

import scala.io.Source
import com.github.plippe.news.scrapy.models.Link

class HardDriveStore[F[_]](implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, Link.HardDrive]
    with Writer[F, Link.HardDrive] {

  def read(link: Link.HardDrive): Stream[F, String] = Stream.eval {
    F.catchNonFatal {
      Source.fromFile(link.path).getLines.mkString("\n")
    }
  }

  def write(link: Link.HardDrive, document: String): Stream[F, Link.HardDrive] =
    Stream.eval {
      F.catchNonFatal {

        val fileRoot = new File(new File(link.path).getParent()).mkdirs()
        locally(fileRoot)
        val file = new File(link.path)
        locally(file)
        val writer = new PrintWriter(new File(link.path))
        writer.write(document)
        writer.close()

        link
      }
    }

}

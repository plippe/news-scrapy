package com.github.plippe.news.scrapy.stores

import cats.ApplicativeError
import java.io.{File, FileWriter}

import scala.io.Source
import com.github.plippe.news.scrapy.models.Link

class HardDriveStore[F[_]](implicit F: ApplicativeError[F, Throwable])
    extends Reader[F, Link.HardDrive, String]
    with Writer[F, Link.HardDrive, String] {

  def read(link: Link.HardDrive): F[String] =
    F.catchNonFatal {
      Source.fromFile(link.path).getLines.mkString("\n")
    }

  def write(link: Link.HardDrive, document: String): F[Link.HardDrive] =
    F.catchNonFatal {
      val file = new File(link.path)

      file.getParentFile().mkdirs()

      val writer = new FileWriter(file)
      writer.write(document)
      writer.close()

      link
    }

}

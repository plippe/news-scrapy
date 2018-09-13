package com.github.plippe.news.scrapy.models

import com.amazonaws.services.s3.AmazonS3URI
import org.http4s.Uri

trait Source

object Source {
  case class AmazonS3(uri: AmazonS3URI) extends Source
  case class HardDrive(path: String) extends Source
  case class Http(uri: Uri) extends Source
}

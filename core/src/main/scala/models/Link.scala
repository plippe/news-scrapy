package com.github.plippe.news.scrapy.models

import com.amazonaws.services.s3.AmazonS3URI
import org.http4s.Uri

sealed trait Link

object Link {
  case class AmazonS3(uri: AmazonS3URI) extends Link
  case class AwsLambda(functionName: String) extends Link
  case class HardDrive(path: String) extends Link
  case class Http(uri: Uri) extends Link
}

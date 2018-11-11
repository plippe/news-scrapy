package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import org.http4s.Uri

class IndependentIeArticleListParser[F[_]]()(
    implicit val F: ApplicativeError[F, Throwable])
    extends UriListParser[F] {

  def validUri(uri: Uri): Boolean = {
    uri.toString.startsWith("https://www.independent.ie/") &&
    uri.toString.endsWith(".html") &&
    !uri.toString.containsSlice("/service/")
  }

}

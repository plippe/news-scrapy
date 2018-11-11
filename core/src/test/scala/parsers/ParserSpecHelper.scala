package com.github.plippe.news.scrapy.parsers

import scala.io.Source
import org.http4s.Uri

import com.github.plippe.news.scrapy.models.WebPage

trait ParserSpecHelper {

  def parse[A](parser: Parser[Either[Throwable, ?], A],
               uri: Uri,
               htmlPath: String): A = {
    val html = Source.fromResource(htmlPath).getLines.mkString("\n")
    val webPage = WebPage(uri, html)

    parser.parse(webPage).right.get
  }

}

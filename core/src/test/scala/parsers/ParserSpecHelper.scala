package com.github.plippe.news.scrapy.parsers

import scala.io.Source

trait ParserSpecHelper {

  def parse[A](parser: Parser[Either[Throwable, ?], A], contentPath: String): A = {
    val content = Source.fromResource(contentPath).getLines.mkString("\n")
    parser.parse(content).right.get
  }

}

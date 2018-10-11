package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import fs2.Stream
import org.jsoup.Jsoup

import collection.JavaConverters._

class GenericArticleParser [F[_]: ApplicativeError[?[_], Throwable]]()
  extends Parser[F, String] {

  def parse(content: String): Stream[F, String] = {
    val text = Jsoup
      .parse(content)
      .select(".body")
      .eachText()
      .asScala
      .mkString

    Stream(text)
  }

}
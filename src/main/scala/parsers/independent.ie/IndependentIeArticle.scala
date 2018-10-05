package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import collection.JavaConverters._
import fs2.Stream
import org.jsoup.Jsoup

class IndependentIeArticleParser[F[_]: ApplicativeError[?[_], Throwable]]()
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

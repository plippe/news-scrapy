package com.github.plippe.news.scrapy.parsers.independent

import cats.ApplicativeError
import com.github.plippe.news.scrapy.parsers.Parser
import fs2.Stream
import org.jsoup.Jsoup

import collection.JavaConverters._

class IrishIndependentIeArticleParser [F[_]: ApplicativeError[?[_], Throwable]]()
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

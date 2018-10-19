package com.github.plippe.news.scrapy.parsers.examiner

import cats.ApplicativeError
import com.github.plippe.news.scrapy.parsers.Parser
import fs2.Stream
import org.jsoup.Jsoup

import collection.JavaConverters._

class IrishExaminerArticleParser [F[_]: ApplicativeError[?[_], Throwable]]()
  extends Parser[F, String] {

  def parse(content: String): Stream[F, String] = {
    val text = Jsoup
      .parse(content)
      .select(".ctx_content")
      .eachText()
      .asScala
      .mkString

    Stream(text)
  }

}
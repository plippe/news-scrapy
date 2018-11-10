package com.github.plippe.news.scrapy.parsers

import cats.Applicative
import org.jsoup.Jsoup

import collection.JavaConverters._

class IrishExaminerComArticleParser[F[_]]()(implicit val F: Applicative[F])
    extends Parser[F, String, String] {

  def parse(content: String): F[String] = {
    val text = Jsoup
      .parse(content)
      .select(".ctx_content")
      .eachText()
      .asScala
      .mkString

    Applicative[F].pure(text)
  }

}

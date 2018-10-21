package com.github.plippe.news.scrapy.parsers

import cats.Applicative
import org.jsoup.Jsoup

import collection.JavaConverters._

class IndependentIeArticleParser [F[_]: Applicative]()
  extends Parser[F, String] {

  def parse(content: String): F[String] = {
    val text = Jsoup
      .parse(content)
      .select(".body")
      .eachText()
      .asScala
      .mkString

    Applicative[F].pure(text)
  }

}

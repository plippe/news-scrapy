package com.github.plippe.news.scrapy.parsers

import cats.Applicative
import java.time.{ZonedDateTime, ZoneId}
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale
import java.time.ZonedDateTime
import org.jsoup.Jsoup
import collection.JavaConverters._
import scala.util.Try

import com.github.plippe.news.scrapy.models.{Article, WebPage}

class IrishExaminerComArticleParser[F[_]: Applicative]()
    extends Parser[F, Article] {

  def parse(webPage: WebPage): F[Article] = {
    val html = Jsoup.parse(webPage.html)

    val body = html
      .select(".ctx_content")
      .eachText()
      .asScala
      .mkString

    val publishedAt = {
      val format = new DateTimeFormatterBuilder()
        .parseCaseInsensitive()
        .appendPattern("EEEE, MMMM d, yyyy - hh:m a")
        .toFormatter()
        .withLocale(Locale.forLanguageTag("EN"))
        .withZone(ZoneId.of("Europe/Dublin"))

      Option(html.selectFirst(".byline"))
        .map(_.text())
        .flatMap { time =>
          Try(ZonedDateTime.parse(time, format)).toOption
            .orElse {
              println(
                s"IrishExaminerComArticleParser failed to parse '${time}'")
              None
            }
        }
        .getOrElse(ZonedDateTime.now())
    }

    Applicative[F].pure(Article(body, webPage.uri, publishedAt))
  }

}

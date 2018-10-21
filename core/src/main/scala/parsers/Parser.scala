package com.github.plippe.news.scrapy.parsers

trait Parser[F[_], A] {
  def parse(content: String): F[A]
}

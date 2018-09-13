package com.github.plippe.news.scrapy.parsers

import fs2.Stream

trait Parser[F[_], T] {
  def parse(content: String): Stream[F, T]
}

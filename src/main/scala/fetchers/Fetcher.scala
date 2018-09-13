package com.github.plippe.news.scrapy.fetchers

import fs2.Stream

import com.github.plippe.news.scrapy.models.Source

trait Fetcher[F[_], T <: Source] {
  def fetch(event: T): Stream[F, String]
}

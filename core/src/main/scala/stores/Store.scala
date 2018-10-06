package com.github.plippe.news.scrapy.stores

import fs2.Stream

import com.github.plippe.news.scrapy.models.Link

trait Reader[F[_], L <: Link] {
  def read(link: L): Stream[F, String]
}

trait Writer[F[_], L <: Link] {
  def write(link: L, document: String): Stream[F, L]
}

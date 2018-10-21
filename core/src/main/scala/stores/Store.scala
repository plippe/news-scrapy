package com.github.plippe.news.scrapy.stores

import com.github.plippe.news.scrapy.models.Link

trait Reader[F[_], L <: Link] {
  def read(link: L): F[String]
}

trait Writer[F[_], L <: Link] {
  def write(link: L, document: String): F[L]
}

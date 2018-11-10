package com.github.plippe.news.scrapy.stores

import com.github.plippe.news.scrapy.models.Link

trait Reader[F[_], L <: Link, D] {
  def read(link: L): F[D]
}

trait Writer[F[_], L <: Link, D] {
  def write(link: L, document: D): F[L]
}

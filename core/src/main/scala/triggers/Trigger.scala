package com.github.plippe.news.scrapy.triggers

import fs2.Stream

import com.github.plippe.news.scrapy.models.Link

trait Trigger[F[_], L <: Link] {
  def trigger(link: L, content: String): Stream[F, Unit]
}

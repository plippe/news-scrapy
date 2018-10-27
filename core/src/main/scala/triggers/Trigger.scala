package com.github.plippe.news.scrapy.triggers

import com.github.plippe.news.scrapy.models.Link

trait Trigger[F[_], L <: Link] {
  def trigger(link: L, content: String): F[Unit]
}

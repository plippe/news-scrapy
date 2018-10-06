package com.github.plippe.news.scrapy.models

trait Event

object Event {
  case class FetchDocument(source: Link) extends Event
  case class ParseDocument(source: Link) extends Event
}

package com.github.plippe.news.scrapy.models

sealed trait Event

object Event {
  case class FetchDocument(source: Link) extends Event
  case class ParseDocument(source: Link) extends Event
}

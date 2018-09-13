package com.github.plippe.news.scrapy.models

trait Event

object Event {
  case class FetchDocument(document: Source) extends Event
  case class ParseDocument(document: Source, origin: Source) extends Event
}

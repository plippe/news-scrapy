package com.github.plippe.news.scrapy.models

import java.time.ZonedDateTime
import org.http4s.Uri

case class WebPage(html: String, readOn: Uri, readAt: ZonedDateTime)

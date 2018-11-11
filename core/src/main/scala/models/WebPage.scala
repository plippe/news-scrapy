package com.github.plippe.news.scrapy.models

import org.http4s.Uri

case class WebPage(uri: Uri, html: String)

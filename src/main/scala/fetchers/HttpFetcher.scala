package com.github.plippe.news.scrapy.fetchers

import fs2.Stream
import org.http4s.client.Client
import org.http4s.Request

import com.github.plippe.news.scrapy.models.Source.Http

class HttpFetcher[F[_]](client: Client[F]) extends Fetcher[F, Http] {

  def fetch(event: Http): Stream[F, String] = {
    val req = Request[F](uri = event.uri)
    client.streaming(req)(resp => resp.bodyAsText)
  }

}

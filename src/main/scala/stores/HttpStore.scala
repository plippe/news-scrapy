package com.github.plippe.news.scrapy.stores

import fs2.Stream
import org.http4s.client.Client
import org.http4s.Request

import com.github.plippe.news.scrapy.models.Link.Http

class HttpStore[F[_]](client: Client[F]) extends Reader[F, Http] {

  def read(link: Http): Stream[F, String] = {
    val req = Request[F](uri = link.uri)
    client.streaming(req)(resp => resp.bodyAsText)
  }

}

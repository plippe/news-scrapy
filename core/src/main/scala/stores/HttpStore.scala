package com.github.plippe.news.scrapy.stores

import fs2.Stream
import org.http4s.client.Client
import org.http4s.Request

import com.github.plippe.news.scrapy.models.Link

class HttpStore[F[_]](client: Client[F]) extends Reader[F, Link.Http] {

  def read(link: Link.Http): Stream[F, String] = {
    val req = Request[F](uri = link.uri)
    client
      .streaming(req)(_.bodyAsText)
      .reduce(_ + _)
  }

}

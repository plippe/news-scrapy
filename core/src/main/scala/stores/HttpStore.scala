package com.github.plippe.news.scrapy.stores

import org.http4s.client.Client
import org.http4s.Request
import cats.effect.Sync

import com.github.plippe.news.scrapy.models.Link

class HttpStore[F[_]](client: Client[F])(implicit F: Sync[F])
    extends Reader[F, Link.Http] {

  def read(link: Link.Http): F[String] = {
    val req = Request[F](uri = link.uri)
    client.expect[String](req)
  }

}

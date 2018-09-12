package com.github.plippe.news.scrapy.pages

import fs2.Stream
import org.http4s.client.Client
import org.http4s.Request

trait Fetcher[F[_]] {
  def fetch(req: Request[F]): Stream[F, String]
}

class DefaultFetcher[F[_]](client: Client[F]) extends Fetcher[F] {
  def fetch(req: Request[F]): Stream[F, String] =
    client.streaming(req)(resp => resp.bodyAsText)
}

trait Parser[F[_], T] {
  def parse(html: String): Stream[F, T]
}

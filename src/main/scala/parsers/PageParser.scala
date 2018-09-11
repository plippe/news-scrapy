package com.github.plippe.news.scrapy.parsers

import fs2.Stream
import org.http4s.client.Client
import org.http4s.Request

trait PageParser[F[_], T] {
  val client: Client[F]

  def parse(req: Request[F]): Stream[F, T] =
    for {
      html <- getHtml(req)
      content <- readHtml(html)
    } yield content

  def getHtml(req: Request[F]): Stream[F, String] =
    client.streaming(req)(resp => resp.bodyAsText)

  def readHtml(html: String): Stream[F, T]
}

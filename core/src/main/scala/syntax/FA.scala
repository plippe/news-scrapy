package com.github.plippe.news.scrapy.syntax

import fs2._

trait FA {

  implicit final def syntaxFA[F[_], A](fa: F[A]) = new SyntaxFA(fa)

  class SyntaxFA[F[_], A](fa: F[A]) {
    def stream(): Stream[F, A] = Stream.eval[F, A](fa)
  }

}

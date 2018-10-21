package com.github.plippe.news.scrapy.syntax

import cats.effect.IO
import fs2._
import org.scalatest._
import org.scalatest.prop.Checkers

class FASpec extends FunSuite with Checkers {

  test("SyntaxFA should convert an F[A] to a Stream[F, A]") {
    check { (a: Int) =>
        val fa: IO[Int] = IO(a)
        val sa: Stream[IO, Int] = new SyntaxFA(fa).stream
        val la: List[Int] = sa.compile.toList.unsafeRunSync

        assert(a == la.head)
        true
    }
  }

}

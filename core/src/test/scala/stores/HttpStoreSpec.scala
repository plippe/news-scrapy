package com.github.plippe.news.scrapy.stores

import cats.effect._
import org.http4s._
import org.scalatest.FunSuite
import org.scalatest.prop.PropertyChecks

import com.github.plippe.news.scrapy.Gen._
import com.github.plippe.news.scrapy.mocks.Http4sClientMock
import com.github.plippe.news.scrapy.models.Link

class HttpStoreSpec extends FunSuite with PropertyChecks {

  test("HttpStore should read the appropriete uri") {
    forAll { (uri: Uri, content: String) =>
      val client = new Http4sClientMock[IO] {
        override def expect[A](req: Request[IO])(
            implicit d: EntityDecoder[IO, A]): IO[A] = {
          assert(uri == req.uri)
          IO.pure(content.asInstanceOf[A])
        }
      }

      val store = new HttpStore(client)
      val result = store.read(Link.Http(uri)).unsafeRunSync

      assert(content == result)
    }
  }

}

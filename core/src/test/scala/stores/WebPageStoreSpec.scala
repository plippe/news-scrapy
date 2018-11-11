package com.github.plippe.news.scrapy.stores

import cats.effect._
import java.io.File
import org.http4s._
import org.scalatest.FunSuite
import org.scalatest.prop.PropertyChecks

import com.github.plippe.news.scrapy.Gen._
import com.github.plippe.news.scrapy.mocks._
import com.github.plippe.news.scrapy.models._

class WebPageSpec extends FunSuite with PropertyChecks {

  test("WebPageHttpStore should read the appropriete uri") {
    forAll { (uri: Uri, html: String) =>
      val client = new Http4sClientMock[IO] {
        override def expect[A](req: Request[IO])(
            implicit d: EntityDecoder[IO, A]): IO[A] = {
          assert(uri == req.uri)
          IO.pure(html.asInstanceOf[A])
        }
      }

      val store = new WebPageHttpStore(client)
      val result = store.read(uri).unsafeRunSync

      assert(result.html == html)
    }
  }

  test("WebPageAwsS3Store should create the appropriete Amazon S3 uri") {
    forAll { (prefix: AwsS3Uri, uri: Uri) =>
      val client = new AmazonS3Mock()
      val store = new WebPageAwsS3Store[IO](client, prefix)
      val result = store.buildAwsS3Uri(prefix, uri)

      assert(result.bucket == prefix.bucket)
    }
  }

  test("WebPageAwsS3Store should read the appropriete Amazon S3 object") {
    forAll { (prefix: AwsS3Uri, uri: Uri, html: String) =>
      val client = new AmazonS3Mock {
        override def getObjectAsString(bucket: String, key: String) = {
          assert(bucket == prefix.bucket)
          assert(key.startsWith(prefix.key))

          html
        }
      }

      val store = new WebPageAwsS3Store[IO](client, prefix)
      val result = store.read(uri).unsafeRunSync

      assert(result.html == html)
    }
  }

  test("WebPageAwsS3Store should write the appropriete Amazon S3 object") {
    forAll { (prefix: AwsS3Uri, uri: Uri, html: String) =>
      val client = new AmazonS3Mock {
        override def putObject(bucket: String, key: String, str: String) = {
          assert(bucket == prefix.bucket)
          assert(key.startsWith(prefix.key))
          assert(str == html)

          null
        }
      }

      val store = new WebPageAwsS3Store[IO](client, prefix)
      store.write(WebPage(uri, html)).unsafeRunSync
    }
  }

  test("WebPageHardDriveStore should create the appropriete file") {
    forAll { (prefix: File, uri: Uri) =>
      val store = new WebPageHardDriveStore[IO](prefix)
      val result = store.buildFile(prefix, uri)

      assert(result.getPath.startsWith(prefix.getPath))
    }
  }

  test("WebPageHardDriveStore should read/write the appropriete file") {
    forAll { (prefix: File, uri: Uri, html: String) =>
      val store = new WebPageHardDriveStore[IO](prefix)
      val file = store.buildFile(prefix, uri)

      assert(!file.exists)

      store.write(WebPage(uri, html)).unsafeRunSync

      assert(file.exists)

      val result = store.read(uri).unsafeRunSync

      assert(result.html == html)

      file.delete
    }
  }
}

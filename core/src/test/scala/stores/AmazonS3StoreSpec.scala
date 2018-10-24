package com.github.plippe.news.scrapy.stores

import cats.effect.IO
import com.amazonaws.services.s3.AmazonS3URI
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import com.github.plippe.news.scrapy.Gen._
import com.github.plippe.news.scrapy.mocks.AmazonS3Mock
import com.github.plippe.news.scrapy.models.Link

class AmazonS3StoreSpec extends FunSuite with Checkers {

  test("AmazonS3StoreSpec should read the appropriete Amazon S3 object") {
    check { (uri: AmazonS3URI, content: String) =>
      val client = new AmazonS3Mock {
        override def getObjectAsString(bucket: String, key: String) = {
          assert(uri.getBucket == bucket)
          assert(uri.getKey == key)

          content
        }
      }
      val store = new AmazonS3Store[IO](client)
      val result = store.read(Link.AmazonS3(uri)).unsafeRunSync

      assert(content == result)
      true
    }
  }

  test("AmazonS3StoreSpec should write the appropriete Amazon S3 object") {
    check { (uri: AmazonS3URI, content: String) =>
      val client = new AmazonS3Mock {
        override def putObject(bucket: String, key: String, str: String) = {
          assert(uri.getBucket == bucket)
          assert(uri.getKey == key)
          assert(content == str)

          null
        }
      }
      val store = new AmazonS3Store[IO](client)
      val link = Link.AmazonS3(uri)
      val result = store.write(link, content).unsafeRunSync

      assert(link == result)
      true
    }
  }

}

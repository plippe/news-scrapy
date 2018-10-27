package com.github.plippe.news.scrapy.stores

import cats.implicits._
import java.io.File
import org.scalatest.FunSuite
import org.scalatest.prop.PropertyChecks
import scala.util.Properties

import com.github.plippe.news.scrapy.models.Link

class HardDriveStoreSpec extends FunSuite with PropertyChecks {

  test("HardDriveStoreSpec should read the appropriete file") {
    forAll { (path: String) =>
      val store = new HardDriveStore[Either[Throwable, ?]]()
      val result = store.read(Link.HardDrive(path))

      assert(result.isLeft)
      assert(result.left.get.getMessage == s"$path (No such file or directory)")
    }
  }

  test("HardDriveStoreSpec should write the appropriete file") {
    val temporaryDirectory = Properties.envOrElse("java.io.tmpdir", "/tmp/")
    forAll { (path: Int, content: String) =>
      val fullPath = s"""${temporaryDirectory}tmp-${path}"""
      val file = new File(fullPath)
      assert(!file.exists)

      val store = new HardDriveStore[Either[Throwable, ?]]()
      val link = Link.HardDrive(fullPath)
      val writeResult = store.write(link, content)

      assert(writeResult.isRight)
      assert(writeResult.right.get == link)
      assert(file.exists)

      val readResult = store.read(link)
      assert(readResult.isRight)
      assert(readResult.right.get == content)

      file.delete
    }
  }

}

package com.github.plippe.news.scrapy

import cats.effect.IO
import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import io.circe.generic.auto._
import io.circe.parser.decode
import java.io.{InputStream, OutputStream}
import scala.concurrent.ExecutionContext
import scala.io.Source

object Lambda extends RequestStreamHandler {

  def handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context): Unit = {
    val inputString = Source.fromInputStream(inputStream).mkString

    println(s"Start processing '${inputString}'")
    decode[Task](inputString)
      .map { task =>
        implicit val ec = ExecutionContext.global
        implicit val cs = IO.contextShift(ec)

        Task.stream[IO](task).compile.drain.unsafeRunSync
      }
      .fold(
        { err =>
          println(s"Failure processing '${inputString}'")
          throw err
        },
        { _ =>
          println(s"Success processing '${inputString}'")
        }
      )
  }

}

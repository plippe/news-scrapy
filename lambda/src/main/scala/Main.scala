package com.github.plippe.news.scrapy

import cats.effect.IO
import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import io.circe.generic.auto._
import io.circe.parser.decode
import java.io.{InputStream, OutputStream}
import scala.io.Source

object Lambda extends RequestStreamHandler {

  def handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context): Unit = {
    val inputString = Source.fromInputStream(inputStream).mkString
    decode[Task](inputString)
      .map(_.run[IO].unsafeRunSync)
      .fold(
        { err =>
          println(s"Error: ${inputString}")
          throw err
        },
        { _ =>
          println("Success")
        }
      )
  }

}

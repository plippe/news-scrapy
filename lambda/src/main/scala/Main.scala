package com.github.plippe.news.scrapy

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import io.circe.generic.auto._
import io.circe.parser.decode
import java.io.{InputStream, OutputStream}
import scala.io.Source

import com.github.plippe.news.scrapy.json._
import com.github.plippe.news.scrapy.models.Event

object Lambda extends RequestStreamHandler {
  def handleRequest(inputStream: InputStream, outputStream: OutputStream, context: Context): Unit = {
    val inputString = Source.fromInputStream(inputStream).mkString
    decode[Event](inputString)
      .flatMap(handleEvent)
      .fold(
        { err =>
          println(s"Error: ${inputString}")
          throw err
        },
        { _ => println("Success") }
      )
  }

  def handleEvent(event: Event): Either[Throwable, Unit] = {
    event match {
      case event: Event.FetchDocument => Right(println(s"FetchDocument ${event}"))
      case event: Event.ParseDocument => Right(println(s"ParseDocument ${event}"))
    }
  }
}

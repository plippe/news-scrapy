package com.github.plippe.news.scrapy.tasks

import cats.effect._
import com.amazonaws.services.lambda.AWSLambdaClientBuilder
import fs2.Stream
import io.circe.generic.auto._
import io.circe.syntax._

import com.github.plippe.news.scrapy.models.Link
import com.github.plippe.news.scrapy.syntax._
import com.github.plippe.news.scrapy.triggers._
import com.github.plippe.news.scrapy.Task

object Orchestrator {

  def stream[F[_]: Concurrent](): Stream[F, Unit] = {
    def trigger(trigger: Trigger[F, Link.AwsLambda],
                task: Task): Stream[F, Unit] =
      trigger
        .trigger(Link.AwsLambda("news-scrapy"), task.asJson.noSpaces)
        .stream

    val awsLambdaClient = AWSLambdaClientBuilder.defaultClient()
    val awsLambdaTrigger = new AwsLambdaTrigger[F](awsLambdaClient)

    trigger(awsLambdaTrigger, Task.IndependentIe) ++
      trigger(awsLambdaTrigger, Task.IrishExaminerCom)
  }

}

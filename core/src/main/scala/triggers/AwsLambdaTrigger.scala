package com.github.plippe.news.scrapy.triggers

import cats.ApplicativeError
import cats.implicits._
import com.amazonaws.services.lambda.AWSLambda
import com.amazonaws.services.lambda.model.{InvokeRequest, InvocationType}

import com.github.plippe.news.scrapy.models.Link

class AwsLambdaTrigger[F[_]](client: AWSLambda)(
    implicit F: ApplicativeError[F, Throwable])
    extends Trigger[F, Link.AwsLambda] {

  def trigger(link: Link.AwsLambda, content: String): F[Unit] = {
    val request = new InvokeRequest()
      .withInvocationType(InvocationType.Event)
      .withFunctionName(link.functionName)
      .withPayload(content)

    F.catchNonFatal(client.invoke(request))
      .map(_ => ())
  }

}

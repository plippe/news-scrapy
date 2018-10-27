package com.github.plippe.news.scrapy.triggers

import cats.implicits._
import com.amazonaws.services.lambda.model.{InvokeRequest, InvocationType}
import org.scalatest._
import org.scalatest.prop.PropertyChecks

import com.github.plippe.news.scrapy.mocks.AwsLambdaMock
import com.github.plippe.news.scrapy.models.Link

class AwsLambdaTriggerSpec extends FunSuite with PropertyChecks {

  test("AwsLambdaTrigger should trigger AWS Lambda") {
    forAll { (functionName: String, content: String) =>
      val client = new AwsLambdaMock {
        override def invoke(request: InvokeRequest) = {
          assert(request.getInvocationType == InvocationType.Event.toString)
          assert(request.getFunctionName == functionName)
          assert(new String(request.getPayload.array) == content)

          null
        }
      }
      val trigger = new AwsLambdaTrigger[Either[Throwable, ?]](client)
      val link = Link.AwsLambda(functionName)
      val result = trigger.trigger(link, content)

      assert(result.isRight)
    }
  }

}

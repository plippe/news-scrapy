package com.github.plippe.news.scrapy

import cats.effect.ConcurrentEffect
import fs2.Stream
import scala.concurrent.ExecutionContext

sealed trait Task

object Task {
  case object Orchestrator extends Task

  case object IndependentIe extends Task
  case object IrishExaminerCom extends Task

  def stream[F[_]: ConcurrentEffect](task: Task)(
      implicit ec: ExecutionContext): Stream[F, Unit] = task match {
    case Orchestrator => tasks.Orchestrator.stream[F]

    case IndependentIe    => tasks.IndependentIe.stream[F]
    case IrishExaminerCom => tasks.IrishExaminerCom.stream[F]
  }
}

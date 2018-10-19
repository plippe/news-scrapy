package com.github.plippe.news.scrapy

import cats.effect.Effect

sealed trait Task {
    def run[F[_]: Effect](): F[Unit]
}

object Task {

    case object IndependentIe extends Task with tasks.IndependentIe

}

package com.github.plippe.news.scrapy.parsers

import cats.implicits._
import org.http4s.Uri
import org.scalatest._

class IrishExaminerComArticleListParserSpec
    extends FunSuite
    with ParserSpecHelper {

  test("IrishExaminerComArticleListParser should return all uris in html file") {
    val expected = Seq(
      "https://www.irishexaminer.com/breakingnews/ireland/north-kerry-divided-after-farmers-killing-877202.html",
      "https://www.irishexaminer.com/breakingnews/ireland/north-kerry-divided-after-farmers-killing-877202.html",
      "https://www.irishexaminer.com/breakingnews/ireland/north-kerry-divided-after-farmers-killing-877202.html",
      "https://www.irishexaminer.com/breakingnews/ireland/former-detective-badly-injured-in-1976-ira-bomb-dies-877187.html",
      "https://www.irishexaminer.com/breakingnews/ireland/former-detective-badly-injured-in-1976-ira-bomb-dies-877187.html",
      "https://www.irishexaminer.com/breakingnews/ireland/former-detective-badly-injured-in-1976-ira-bomb-dies-877187.html",
      "https://www.irishexaminer.com/breakingnews/ireland/casey-to-remain-in-aras-race-govt-warns-against-complacency-as-higgins-in-pole-position-877130.html",
      "https://www.irishexaminer.com/breakingnews/ireland/casey-to-remain-in-aras-race-govt-warns-against-complacency-as-higgins-in-pole-position-877130.html",
      "https://www.irishexaminer.com/breakingnews/ireland/casey-to-remain-in-aras-race-govt-warns-against-complacency-as-higgins-in-pole-position-877130.html",
      "https://www.irishexaminer.com/breakingnews/ireland/vicky-phelan-among-winners-at-the-irish-tatler-women-of-the-year-awards-876933.html",
      "https://www.irishexaminer.com/breakingnews/ireland/vicky-phelan-among-winners-at-the-irish-tatler-women-of-the-year-awards-876933.html",
      "https://www.irishexaminer.com/breakingnews/ireland/vicky-phelan-among-winners-at-the-irish-tatler-women-of-the-year-awards-876933.html",
      "https://www.irishexaminer.com/breakingnews/world/ryanair-criticised-for-failing-to-remove-racist-passenger-from-flight-877161.html",
      "https://www.irishexaminer.com/breakingnews/world/ryanair-criticised-for-failing-to-remove-racist-passenger-from-flight-877161.html",
      "https://www.irishexaminer.com/breakingnews/world/ryanair-criticised-for-failing-to-remove-racist-passenger-from-flight-877161.html",
      "https://www.irishexaminer.com/breakingnews/sport/gaa/clonoulty-rossmore-win-first-title-in-21-years-after-thrilling-final-success-877207.html",
      "https://www.irishexaminer.com/breakingnews/sport/gaa/clonoulty-rossmore-win-first-title-in-21-years-after-thrilling-final-success-877207.html",
      "https://www.irishexaminer.com/breakingnews/sport/gaa/clonoulty-rossmore-win-first-title-in-21-years-after-thrilling-final-success-877207.html",
      "https://www.irishexaminer.com/breakingnews/sport/rugby/leinsters-european-winning-run-over-as-toulouse-stun-champions-877204.html",
      "https://www.irishexaminer.com/breakingnews/sport/rugby/leinsters-european-winning-run-over-as-toulouse-stun-champions-877204.html",
      "https://www.irishexaminer.com/breakingnews/sport/rugby/leinsters-european-winning-run-over-as-toulouse-stun-champions-877204.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/i-hope-to-give-some-hope-to-others-selma-blair-reveals-ms-diagnosis-877127.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/i-hope-to-give-some-hope-to-others-selma-blair-reveals-ms-diagnosis-877127.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/i-hope-to-give-some-hope-to-others-selma-blair-reveals-ms-diagnosis-877127.html",
      "https://www.irishexaminer.com/breakingnews/property/living-large-in-huge-sea-front-mansion-876693.html",
      "https://www.irishexaminer.com/breakingnews/property/living-large-in-huge-sea-front-mansion-876693.html",
      "https://www.irishexaminer.com/breakingnews/property/living-large-in-huge-sea-front-mansion-876693.html",
    ).map(Uri.unsafeFromString)

    val parsed = parse(
      new IrishExaminerComArticleListParser(),
      "parsers/irishexaminer.com/index.html"
    )

    assert(expected == parsed)
  }

}

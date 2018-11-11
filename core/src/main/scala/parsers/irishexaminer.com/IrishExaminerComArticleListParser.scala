package com.github.plippe.news.scrapy.parsers

import cats.ApplicativeError
import org.http4s.Uri

class IrishExaminerComArticleListParser[F[_]]()(
    implicit val F: ApplicativeError[F, Throwable])
    extends UriListParser[F] {

  def validUri(uri: Uri): Boolean = {
    uri.toString.startsWith("https://www.irishexaminer.com/") &&
    uri.toString.endsWith(".html") &&
    !ignoredUris.contains(uri) &&
    !uri.toString.containsSlice("/maintopics/")
  }

  val ignoredUris = List(
    "https://www.irishexaminer.com/breakingnews/ireland/postal-delivery-enquiry-form-830443.html",
    "https://www.irishexaminer.com/breakingnews/ireland/help-830450.html",
    "https://www.irishexaminer.com/breakingnews/ireland/contact-details-830449.html",
    "https://www.irishexaminer.com/breakingnews/ireland/cookies-and-how-they-benefit-you-830435.html",
    "https://www.irishexaminer.com/breakingnews/ireland/FAQ-830933.html",
    "https://www.irishexaminer.com/breakingnews/ireland/media-pack-830446.html",
    "https://www.irishexaminer.com/breakingnews/ireland/confidentiality-and-privacy-policy-830432.html",
    "https://www.irishexaminer.com/breakingnews/ireland/syndication-830438.html",
    "https://www.irishexaminer.com/breakingnews/ireland/subscriptions-830926.html",
    "https://www.irishexaminer.com/breakingnews/ireland/terms-and-conditions-of-use-830447.html",
  ).map(Uri.unsafeFromString)

}

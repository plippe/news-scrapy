package com.github.plippe.news.scrapy.parsers

import cats.implicits._
import java.time.{ZonedDateTime, ZoneId}
import org.http4s.Uri
import org.scalatest._

import com.github.plippe.news.scrapy.models.Article

class IndependentIeArticleParserSpec extends FunSuite with ParserSpecHelper {

  test("IndependentIeArticleParser should return the body") {
    val expected = Article(
      """EXPEDITION: A farmer sits outside his home in Peru Eamon Dillon October 21 2018 2:30 AM 0 Comments Intrepid explorer launches new book to raise funds for homeless Independent.ie An archaeologist, who has travelled to some of the world's most remote locations, has put together a book of stunning photographs to raise funds for the homeless. https://www.independent.ie/irish-news/intrepid-explorer-launches-new-book-to-raise-funds-for-homeless-37441885.html https://www.independent.ie/incoming/article37441367.ece/96030/AUTOCROP/h342/10explorer.jpg Email An archaeologist, who has travelled to some of the world's most remote locations, has put together a book of stunning photographs to raise funds for the homeless. Vincent Butler's book, Sixty Photographs for Simon, is a collection of images he has taken during his expeditions over the past two decades. These include photos taken in the Arctic, Scandinavia, Europe, Africa, Asia, South America and the Antarctic. The Dublin native is a professional archaeologist who worked on the Wood Quay excavations in Dublin, and in the National Museum of Ireland. His association with the Simon Community began years ago and kicked off his career as an expedition leader. "Almost 20 years ago, in 2000, I went on a charity trek across the Sahara in Morocco to raise money for Dublin Simon Community," said Vincent. During that trip, the group stumbled across the remains of a prehistoric site that had not been previously discovered. The cover of the book in aid of the Simon Community "That discovery, on the Dublin Simon Sahara Trek, led to my career as a global expedition leader, and changed my life. "I know how the Simon Community changes the lives of homeless people today, and I want to raise money, with this book, to help them," he explained. Tim Severin, the Irish explorer made famous for his 'Brendan Voyage' across the North Atlantic in the 1970s in a skin-covered boat, wrote the foreword to the book. "Vinnie's photographs are glimpses of a world, full of interest, variety and colour. It follows that we should do our best to preserve and protect its diversity. "At the same time, he draws attention to the fact that we can make a difference much closer to home," wrote Severin. As well as his work as an expedition leader, Vincent has also worked as a consultant to Failte Ireland as a tour guide trainer. He's an expert on the Burren and Viking archaeology and travels widely as a lecturer on board the expedition cruise ship -National Geographic Explorer. Sixty Photographs For Simon is being launched in the 'Great Room' of the Shelbourne Hotel, Dublin, this Tuesday at 7pm. All proceeds from the sale of the book (hardback, full colour, €20 per copy) will go to the Simon Community. Sunday Independent Follow @Independent_ie""",
      Uri.uri("http://example.com/"),
      ZonedDateTime.of(2018, 10, 21, 2, 30, 0, 0, ZoneId.of("Europe/Dublin")),
    )

    val parsed = parse(
      new IndependentIeArticleParser(),
      expected.publishedOn,
      "parsers/independent.ie/article.html",
    )

    assert(expected == parsed)
  }

}

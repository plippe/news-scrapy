package com.github.plippe.news.scrapy.parsers

import cats.implicits._
import java.time.{ZonedDateTime, ZoneId}
import org.http4s.Uri
import org.scalatest._

import com.github.plippe.news.scrapy.models.Article

class IrishExaminerComArticleParserSpec extends FunSuite with ParserSpecHelper {

  test("IrishExaminerComArticleParser should return the body") {
    val expected = Article(
      """By Anne Lucey It was a killing that shattered two families, stunned a community and now has divided a county. Michael Ferris was found guilty on Friday of the manslaughter of 73-year-old tillage farmer John Anthony O’Mahony. Ferris (63) had admitted to gardaí to setting out to kill, or at least seriously injury Mr O’Mahony, on the morning of April 4, 2017. He was acquitted of murder but found guilty of manslaughter at the Central Criminal Court sitting in Tralee after a two-week intense trial. Michael Ferris The court heard, after what he said was 30 years of listening to the shotgun-like sounds of a crow banger, Ferris just snapped and drove the forks of his teleporter through the window of O'Mahony's car as “it was no good talking to him”. Ferris said there was no other way to stop his neighbour from using the loud crow banger - a machine used to frighten birds from crops. In custody since April 2017, Ferris will be sentenced in November. However, there has been strong reaction to the character assassination of the dead man, who was painted as difficult and awkward and as someone out of control throughout the trial. Many in north Kerry feel the true picture of the late Anthony O’Mahony did not emerge during the trial and are disturbed that a caricature of some kind of ogre was painted. READ MORE: Kerry farmer found guilty of manslaughter of neighbour The picture they paint of O’Mahony is that of the hurler, reader, ground breaking farmer and intelligent man. Yes, he could be odd, but that was because of his devotion to his crops, they said. He grew tomatoes and other vegetables and plants under glass in Rattoo when no-one else did and supplied markets in Dublin and London. He hurled with Ballyduff in his youth and had a great love of sport and the GAA. He was keen on horse racing and enjoyed a flutter. He read the Irish Independent each day and bought a number of farm magazines each week. He had a family of nieces and nephews and a brother and sister, who loved him, all highly respected people. At least one person recalled how he had studied agriculture in Warrenstown college 50 years ago and how one of his former teachers had so much respect for him he attended his funeral. “He was a fanatic about husbandry, a real purist, a perfectionist. He was obsessed about his crops. He was meticulous. What was going on in Rattoo was not about getting at the people," the local said. There is also some surprise in North Kerry that no-one approached gardaí to complain about Mr O’Mahony use of the crow banger. Seamus (brother) and Margaret O'Mahony with Angela (sister) and Christy Houlihan after the Requiem mass last year for Anthony O'Mahony (inset). According to the trial, the only authority approached was Kerry County Council about the noise. The prosecution argued that the killing had been deliberate and intentional and pressed for a murder conviction. However, the defence had argued there had been accumulated provocation because of the behaviour of Mr O’Mahony, and the fair and just verdict would be manslaughter. Defence counsel Brendan Grehan SC said in his closing speech that he made no apology for speaking ill of the dead, which was not a normal thing to do. However, it was to show why Michael Ferris — “a good man who did a bad thing” — did what he did. Mr O’Mahony’s family shook their heads and cried as the verdict was read out. Ferris told gardaí he set out on the morning of April 4, 2017 and parked his teleporter, an industrial-type machine normally used in construction which had two large prongs at the front, across the road to block Mr O’Mahony’s car. He went away and did a few jobs and when he heard the hooting on the laneway, he knew “it was Mahony”. He got back into the teleporter and drove the prongs of the machine through the windscreen of the car. The trial was told the car had been lifted “clear off the ground” and dropped a number of times, that the huge forks of the teleporter had been driven in and out through the body of Mr O’Mahony and through the car a number of times, and that some of the piercings on the bonnet and the roof had blood stains. State pathologist Margot Bolster spent an hour detailing the catastrophic and horrific injuries to a silent courtroom on the third day of the trial. “He (O'Mahony) has been torn to shreds not just in the physical sense. His character was torn to shreds last week, before the nation, and this is causing terrible upset to the family,” one prominent local said.""",
      Uri.uri("http://example.com/"),
      ZonedDateTime.of(2018, 10, 21, 16, 52, 0, 0, ZoneId.of("Europe/Dublin")),
    )

    val parsed = parse(
      new IrishExaminerComArticleParser(),
      expected.publishedOn,
      "parsers/irishexaminer.com/article.html",
    )

    assert(expected == parsed)
  }

}

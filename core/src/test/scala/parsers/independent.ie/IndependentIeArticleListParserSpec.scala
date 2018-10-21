package com.github.plippe.news.scrapy.parsers

import cats.implicits._
import org.http4s.Uri
import org.scalatest._

class IndependentIeArticleListParserSpec extends FunSuite with ParserSpecHelper {

  test("IndependentIeArticleListParser should return all uris in html file") {
    val expected = Seq(
      "https://www.independent.ie/irish-news/intrepid-explorer-launches-new-book-to-raise-funds-for-homeless-37441885.html",
      "https://www.independent.ie/irish-news/courts/killer-farmer-showed-no-regret-no-remorse-37439818.html",
      "https://www.independent.ie/irish-news/courts/kerry-killer-farmer-trial-focused-on-character-of-victim-a-man-whod-fallen-out-with-neighbours-37439624.html",
      "https://www.independent.ie/world-news/middle-east/jamal-khashoggi-died-in-a-fight-at-saudi-consulate-staterun-media-37439688.html",
      "https://www.independent.ie/world-news/saudi-consulate-staff-quizzed-in-turkeys-missing-journalist-inquiry-37438105.html",
      "https://www.independent.ie/world-news/turkish-police-search-forest-coastal-city-for-saudi-journalist-khashoggis-remains-37436762.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/shut-your-trap-casey-tells-indian-leo-varadkar-for-trying-to-impact-poll-37439628.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/traveller-controversy-wacky-racer-has-started-a-debate-we-need-to-have-37439648.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/candidate-keeps-on-playing-his-games-despite-talk-of-suspending-campaign-37439652.html",
      "https://www.independent.ie/sport/gaelic-games/hurling/i-could-hardly-lift-my-hands-davy-fitzgeralds-depression-after-sleep-disorder-37439594.html",
      "https://www.independent.ie/sport/gaelic-games/hurling/davy-fitzgerald-ill-never-forgive-brian-lohan-and-jamesie-oconnor-for-how-they-treated-me-as-clare-manager-37439782.html",
      "https://www.independent.ie/sport/gaelic-games/hurling/davy-fitzgerald-ive-had-scares-and-im-not-ashamed-to-admit-that-im-afraid-of-dying-37439757.html",
      "https://www.independent.ie/irish-news/politics/abortion-will-become-the-contraception-of-choice-fianna-fail-td-marc-mcsharry-in-abortion-uturn-37439645.html",
      "https://www.independent.ie/irish-news/news/no-irish-no-gay-banner-hung-over-motorway-being-treated-as-a-hate-crime-37439460.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/president-higgins-still-on-course-to-win-second-presidential-term-poll-37441150.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/peter-casey-suspends-campaign-for-presidency-following-controversial-traveller-comments-37437133.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/fiery-exchanges-mark-first-presidential-debate-featuring-all-six-candidates-37432534.html",
      "https://www.independent.ie/irish-news/george-hook-case-of-eric-9-is-truly-terrible-but-he-should-be-deported-its-the-law-37438985.html",
      "https://www.independent.ie/irish-news/dcu-president-adds-voice-to-calls-on-justice-minister-for-review-of-deportation-case-of-student-37438830.html",
      "https://www.independent.ie/irish-news/news/eric-is-irish-harris-calls-for-halt-to-deportation-of-boy-9-37436533.html",
      "https://www.independent.ie/world-news/europe/european-court-orders-poland-to-suspend-clearout-of-its-judges-37439845.html",
      "https://www.independent.ie/world-news/europe/britain/notorious-uk-hate-preacher-out-of-jail-37439564.html",
      "https://www.independent.ie/news/environment/littering-gets-worse-in-disadvantaged-areas-of-our-biggest-cities-37419588.html",
      "https://www.independent.ie/news/environment/revealed-trouble-brewing-for-beer-drinkers-as-climate-change-to-double-prices-37424465.html",
      "https://www.independent.ie/news/environment/water-regulator-to-examine-300-million-litres-of-water-a-day-from-shannon-plan-before-project-signoff-37435741.html",
      "https://www.independent.ie/news/environment/50-flood-defence-schemes-to-get-underway-in-atrisk-towns-and-cities-plan-reveals-36869663.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/peter-casey-to-stay-in-aras-race-as-he-slams-welfaredependent-state-37441787.html",
      "https://www.independent.ie/irish-news/news/significant-structural-issues-to-force-part-of-dublin-school-to-close-immediately-37442314.html",
      "https://www.independent.ie/irish-news/mystery-damage-and-laurel-and-hardy-tactics-an-insight-into-irelands-car-rental-market-37431606.html",
      "https://www.independent.ie/sport/other-sports/boxing/katie-taylor-dominates-cindy-serrano-to-retain-titles-with-flawless-performance-in-boston-37442115.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/shut-your-trap-casey-tells-indian-leo-varadkar-for-trying-to-impact-poll-37439628.html",
      "https://www.independent.ie/life/family/mothers-babies/dear-meghan-markle-heres-my-advice-from-one-expectant-mum-to-another-37427760.html",
      "https://www.independent.ie/life/is-the-tradition-of-the-unannounced-house-visit-dying-in-ireland-37427251.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/nicola-anderson-peter-casey-left-quickly-after-getting-the-exposure-hed-come-for-37436253.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/peter-casey-to-stay-in-aras-race-as-he-slams-welfaredependent-state-37441787.html",
      "https://www.independent.ie/irish-news/health/doctors-fear-over-mental-health-care-for-children-37441799.html",
      "https://www.independent.ie/irish-news/highprofile-property-developer-johnny-ronan-will-appeal-again-as-tower-plan-is-rejected-37441792.html",
      "https://www.independent.ie/irish-news/news/president-higgins-faces-calls-to-come-clean-on-reasons-for-travelling-to-belfast-on-government-plane-37441248.html",
      "https://www.independent.ie/irish-news/our-family-is-broken-desperate-mother-writes-to-health-minister-for-help-with-son-10-who-is-selfharming-37437816.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/shut-your-trap-casey-tells-indian-leo-varadkar-for-trying-to-impact-poll-37439628.html",
      "https://www.independent.ie/irish-news/courts/killer-farmer-showed-no-regret-no-remorse-37439818.html",
      "https://www.independent.ie/irish-news/politics/high-mortgage-costs-top-list-in-ffs-supply-talks-concern-in-talks-37441880.html",
      "https://www.independent.ie/world-news/north-america/president-trump/trump-says-saudi-arrests-over-journalist-jamal-khashoggi-death-a-good-first-step-37440747.html",
      "https://www.independent.ie/world-news/europe/britain/the-will-of-the-people-is-changing-more-than-500000-march-for-second-brexit-vote-37440718.html",
      "https://www.independent.ie/world-news/europe/european-court-orders-poland-to-suspend-clearout-of-its-judges-37439845.html",
      "https://www.independent.ie/world-news/europe/thousands-of-britons-whose-families-fled-nazis-now-applying-for-german-passports-37439842.html",
      "https://www.independent.ie/world-news/europe/britain/youtube-sparks-rise-in-antique-gun-crimes-37439831.html",
      "https://www.independent.ie/world-news/north-america/allen-has-been-publicly-lynched-over-abuse-claims-says-bardem-37439829.html",
      "https://www.independent.ie/world-news/europe/britain/city-worker-banned-from-every-london-pub-after-racist-attack-37439830.html",
      "https://www.independent.ie/world-news/europe/european-court-orders-poland-to-suspend-clearout-of-its-judges-37439845.html",
      "https://www.independent.ie/opinion/columnists/ivan-yates/brexit-impasse-leaves-us-stuck-with-the-worst-dil-in-history-37439650.html",
      "https://www.independent.ie/business/brexit/wild-geese-to-return-early-but-its-not-clear-where-we-are-going-to-put-them-37419321.html",
      "https://www.independent.ie/irish-news/presidential-election-2018/the-biggest-change-is-that-i-dont-have-privacy-any-more-michael-d-higgins-37417488.html",
      "https://www.independent.ie/business/technology/broadband-plan-hangs-by-a-thread-as-rural-ireland-pays-the-price-37417487.html",
      "https://www.independent.ie/opinion/columnists/dan-obrien/as-brexit-tears-irishuk-relations-apart-are-upsides-to-commonwealth-growing-37417541.html",
      "https://www.independent.ie/opinion/columnists/brendan-oconnor/brendan-oconnor-making-politics-great-again-37417445.html",
      "https://www.independent.ie/opinion/comment/celtic-warrior-who-fought-a-selfless-fight-37417573.html",
      "https://www.independent.ie/opinion/columnists/john-downing/masses-now-ready-to-hit-the-streets-and-demand-peoples-vote-but-its-too-little-too-late-37439654.html",
      "https://www.independent.ie/sponsored-features/taking-the-leap-37425441.html",
      "https://www.independent.ie/sponsored-features/you-could-win-1bn-in-lottery-prizes-this-week-without-leaving-ireland-37409136.html",
      "https://www.independent.ie/sponsored-features/international-dental-clinic-of-the-year-2018-winner-announced-37381333.html",
      "https://www.independent.ie/sponsored-features/helping-you-to-find-your-fit-37308831.html",
      "https://www.independent.ie/sponsored-features/live-life-and-treat-yourself-guiltfree-37304577.html",
      "https://www.independent.ie/sponsored-features/win-a-training-session-for-your-gaa-team-with-allireland-winning-cork-footballer-daniel-goulding-37157805.html",
      "https://www.independent.ie/sponsored-features/calm-after-the-wild-37101043.html",
      "https://www.independent.ie/sponsored-features/irelands-rivers-lakes-and-beaches-natural-treasures-for-us-all-to-enjoy-36756026.html",
      "https://www.independent.ie/sponsored-features/rethinking-consumption-our-choices-can-make-a-difference-36709390.html",
    ).map(Uri.unsafeFromString)

    val parsed = parse(
      new IndependentIeArticleListParser(),
      "parsers/independent.ie/index.html"
    )

    assert(expected == parsed)
  }

}

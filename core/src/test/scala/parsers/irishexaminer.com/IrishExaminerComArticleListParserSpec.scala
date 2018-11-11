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
      "https://www.irishexaminer.com/breakingnews/ireland/former-detective-badly-injured-in-1976-ira-bomb-dies-877187.html",
      "https://www.irishexaminer.com/breakingnews/ireland/casey-to-remain-in-aras-race-govt-warns-against-complacency-as-higgins-in-pole-position-877130.html",
      "https://www.irishexaminer.com/breakingnews/ireland/vicky-phelan-among-winners-at-the-irish-tatler-women-of-the-year-awards-876933.html",
      "https://www.irishexaminer.com/breakingnews/world/ryanair-criticised-for-failing-to-remove-racist-passenger-from-flight-877161.html",
      "https://www.irishexaminer.com/breakingnews/sport/gaa/clonoulty-rossmore-win-first-title-in-21-years-after-thrilling-final-success-877207.html",
      "https://www.irishexaminer.com/breakingnews/sport/rugby/leinsters-european-winning-run-over-as-toulouse-stun-champions-877204.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/i-hope-to-give-some-hope-to-others-selma-blair-reveals-ms-diagnosis-877127.html",
      "https://www.irishexaminer.com/breakingnews/property/living-large-in-huge-sea-front-mansion-876693.html",
      "https://www.irishexaminer.com/breakingnews/ireland/man-arrested-in-connection-with-alleged-daytime-rape-of-75-year-old-woman-877208.html",
      "https://www.irishexaminer.com/breakingnews/ireland/part-of-dublin-school-building-to-close-after-significant-structural-issues-found-877164.html",
      "https://www.irishexaminer.com/breakingnews/ireland/man-arrested-over-paramilitary-style-shooting-in-the-north-877189.html",
      "https://www.irishexaminer.com/breakingnews/ireland/little-heroes-help-raise-thousands-for-sinkhole-hit-monaghan-gaa-club-877183.html",
      "https://www.irishexaminer.com/breakingnews/ireland/former-fine-gael-td-seymour-crawford-dies-aged-74-877172.html",
      "https://www.irishexaminer.com/viewpoints/columnists/louise-oneill/west-cork-women-against-violence-centre-fighting-on-behalf-of-us-all-473384.html",
      "https://www.irishexaminer.com/viewpoints/columnists/michael-clifford/from-dragon-fire-to-almost-the-farce-473383.html",
      "https://www.irishexaminer.com/viewpoints/columnists/daniel-mcconnell/why-do-women-still-face-paternalismin-politics-473382.html",
      "https://www.irishexaminer.com/viewpoints/columnists/alison-oconnor/casey-scrapes-bottom-of-the-barrel-being-bottom-in-race-to-aras-473379.html",
      "https://www.irishexaminer.com/breakingnews/world/passengers-safely-discharged-after-ferry-runs-aground-off-isle-of-wight-877201.html",
      "https://www.irishexaminer.com/breakingnews/world/billy-connolly-scottish-independence-may-be-the-way-to-go-after-brexit-disaster-877215.html",
      "https://www.irishexaminer.com/breakingnews/world/migrants-resume-advance-towards-us-border-after-crossing-into-mexico-877205.html",
      "https://www.irishexaminer.com/breakingnews/world/update-taiwan-train-derailment-kills-22-injures-170-877171.html",
      "https://www.irishexaminer.com/breakingnews/world/us-nuclear-treaty-pullout-would-be-very-dangerous-step-says-moscow-877184.html",
      "https://www.irishexaminer.com/breakingnews/sport/katie-taylor-defends-world-titles-with-dominant-win-over-serrano-877129.html",
      "https://www.irishexaminer.com/breakingnews/sport/glasgow--and-saracens--the-winners-as-cardiff-blues-are-beaten-877214.html",
      "https://www.irishexaminer.com/breakingnews/sport/fermoy-back-in-the-big-time-premier-ifc-triumph-over-st-michaels-877212.html",
      "https://www.irishexaminer.com/breakingnews/sport/cora-staunton-scores-2-8-as-carnacon-win-mayo-title-in-style-877210.html",
      "https://www.irishexaminer.com/breakingnews/sport/strong-finish-sees-ballyea-secure-second-title-877206.html",
      "https://www.irishexaminer.com/sport/columnists/pat-keane/too-darn-hot-just-too-darn-short-473387.html",
      "https://www.irishexaminer.com/sport/columnists/larry-ryan/do-we-have-the-players-lets-find-out-473386.html",
      "https://www.irishexaminer.com/sport/columnists/liam-mackey/look-on-his-mighty-works-and-despair-473385.html",
      "https://www.irishexaminer.com/sport/columnists/ronan-ogara/persevere-andthe-perception-may-changeit-should-change-473380.html",
      "https://www.irishexaminer.com/breakingnews/views/analysis/has-anyone-seen-roman-abramovich-876922.html",
      "https://www.irishexaminer.com/breakingnews/views/analysis/presidential-campaign-good-for-democracy-but-still-damaging-876942.html",
      "https://www.irishexaminer.com/breakingnews/views/analysis/comment-ignorance-of-role-reveals-dangers-of-celebrity-culture-876957.html",
      "https://www.irishexaminer.com/breakingnews/views/analysis/waiting-for-brexit-is-like-waiting-for-godot--with-delay-and-inaction-the-order-of-the-day-876920.html",
      "https://www.irishexaminer.com/breakingnews/views/analysis/do-human-rights-matter-to-trump-876709.html",
      "https://www.irishexaminer.com/breakingnews/specialreports/mohammed-bin-salman-a-carefully-managed-rise-hidesa-dark-side-876487.html",
      "https://www.irishexaminer.com/breakingnews/specialreports/child-welfare-cases-inefficient-and-adversarial-875979.html",
      "https://www.irishexaminer.com/breakingnews/specialreports/climate-change-15c-is-worth-striving-for-but-is-it-feasible-875831.html",
      "https://www.irishexaminer.com/breakingnews/specialreports/wrc-cases-trying-to-solve-problems-in-the-workplace-easier-said-than-done-875767.html",
      "https://www.irishexaminer.com/breakingnews/specialreports/budget19-extra-funds-to-build-and-buy-social-housing-874769.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/danny-john-jules-scores-first-strictly-come-dancing-10-of-the-series-877133.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/strictly-continues-to-ride-high-in-ratings-after-seann-walsh-controversy-877166.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/david-beckham-explains-why-marriage-to-victoria-is-hard-work-877162.html",
      "https://www.irishexaminer.com/breakingnews/entertainment/x-factor-contestant-forced-to-restart-song-after-announcement-blunder-877150.html",
      "https://www.irishexaminer.com/examviral/british-forrest-gump-takes-on-marathon-coast-to-coast-us-run-877137.html",
      "https://www.irishexaminer.com/examviral/8-mundane-but-mind-blowing-epiphanies-877063.html",
      "https://www.irishexaminer.com/examviral/widows-lakeside-note-breaks-hearts-on-twitter-877047.html",
      "https://www.irishexaminer.com/examviral/11-brilliant-and-entertaining-ways-to-shut-down-a-conversation-876988.html",
      "https://www.irishexaminer.com/examviral/look-inside-glenda-gilsons-baby-shower-876945.html",
      "https://www.irishexaminer.com/breakingnews/business/bank-of-england-remains-under-fire-over-staggeringly-high-expenses-877152.html",
      "https://www.irishexaminer.com/breakingnews/business/former-deutsche-bank-trader-extradited-to-uk-on-rate-rigging-charge-877176.html",
      "https://www.irishexaminer.com/breakingnews/business/markets-braced-for-italy-and-saudi-tensions-877017.html",
      "https://www.irishexaminer.com/breakingnews/business/interest-rate-hike-looms-in-just-11-months-877018.html",
      "https://www.irishexaminer.com/breakingnews/business/food-firms-look-to-europe-877015.html",
      "https://www.irishexaminer.com/breakingnews/lifestyle/6-secrets-for-a-smoother-journey-when-flying-with-a-newborn-baby-877155.html",
      "https://www.irishexaminer.com/breakingnews/lifestyle/get-the-look-the-stunning-autumn-colours-to-stand-out-from-the-crowd-876978.html",
      "https://www.irishexaminer.com/breakingnews/lifestyle/saying-yes-to-the-dress-behind-the-scenes-at-the-royal-wedding-876983.html",
      "https://www.irishexaminer.com/breakingnews/lifestyle/meet-paul-charles--the-agent-to-the-stars-876979.html",
      "https://www.irishexaminer.com/breakingnews/lifestyle/mixing-it-up-at-top-of-world-the-cork-bartender-going-global-876982.html",
      "https://www.irishexaminer.com/examviral/you-need-to-watch-this-weather-shows-hellish-mixed-reality-wildfire-warning-876927.html",
      "https://www.irishexaminer.com/competitions/sign-up-to-our-newsletter-11.html",
      "https://www.irishexaminer.com/breakingnews/ireland/mother-begs-for-counselling-to-save-suicidal-son-10-877039.html",
      "https://www.irishexaminer.com/breakingnews/ireland/farmer-snapped-after-30-years-of-listening-to-neighbours-machine-877032.html",
      "https://www.irishexaminer.com/breakingnews/ireland/fees-for-charging-electric-vehicles-suggested-876963.html",
      "https://www.irishexaminer.com/breakingnews/ireland/girl-injured-following-fall-when-tram-surfing-settles-personal-injury-claim-for-550000-876892.html",
      "https://www.irishexaminer.com/breakingnews/ireland/polls-show-major-backing-for-michael-d-higgins-and-boost-for-fianna-fail-877103.html",
      "https://www.irishexaminer.com/breakingnews/ireland/psni-dispute-president-higgins-security-claims-for-belfast-trip-877108.html",
      "https://www.irishexaminer.com/breakingnews/ireland/gay-cake-bakery-closes-belfast-branch-876872.html",
      "https://www.irishexaminer.com/breakingnews/ireland/woman-running-massage-parlours-thought-manual-relief-was-legal-court-hears-876917.html",
      "https://www.irishexaminer.com/breakingnews/ireland/peter-casey-is-only-saying-what-everyone-else-is-thinking-says-cork-supporter-876847.html",
      "https://www.irishexaminer.com/breakingnews/ireland/gardai-seriously-concerned-about-welfare-of-man-missing-from-wexford-877080.html",
    ).map(Uri.unsafeFromString)

    val parsed = parse(
      new IrishExaminerComArticleListParser(),
      Uri.uri("https://www.irishexaminer.com/"),
      "parsers/irishexaminer.com/index.html"
    )

    assert(expected == parsed)
  }

}

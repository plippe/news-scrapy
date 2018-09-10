import scrapy
from scrapy.crawler import CrawlerProcess
from scrapy.exceptions import DropItem
from datetime import datetime

class IndependentSpider(scrapy.Spider):
    name = 'IndependentSpider'
    start_urls = ['https://www.independent.ie/news/']

    def parse(self, response):
        for link in response.css('article a::attr(href)'):
            yield response.follow(link, self.parse_page)

    def parse_page(self, response):
        title = response.css('article h1').extract_first()
        body = response.css('article .body').extract_first()

        timestamp = datetime.now().strftime('%s')

        yield { 'url': response.url, 'content': title + body, 'timestamp': timestamp }

if __name__ == '__main__':
    process = CrawlerProcess({
        'FEED_FORMAT': 'jsonlines',
        'FEED_URI': './log.json',
    })

    process.crawl(IndependentSpider)
    process.start()

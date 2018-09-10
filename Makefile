.PHONY: run

DOCKER_IMAGE := plippe/news-scrapy

run:
	docker build --tag $(DOCKER_IMAGE) .
	docker run \
		--rm \
		--tty \
		--interactive \
		--volume $(PWD):/opt/repository \
		--workdir /opt/repository \
		$(DOCKER_IMAGE) python myspider.py

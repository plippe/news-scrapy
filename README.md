# news-scrapy

```
TIMER -> FETCH EVENT -> FETCH SNS

FETCH SNS -> FETCH SQS -> LAMBDA -> S3 + PARSE SNS

PARSE SNS -> PARSE SQS -> LAMBDA -> FETCH EVENT -> FETCH SNS
                                 -> PARSE  OBJ  ->    ???    -> PROFIT
```

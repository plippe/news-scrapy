# news-scrapy

[![Build Status](https://app.wercker.com/status/df3ac0cce10e4c031345985aa43803bb/s/master)](https://app.wercker.com/project/byKey/df3ac0cce10e4c031345985aa43803bb)

```
TIMER -> FETCH EVENT -> FETCH SNS

FETCH SNS -> FETCH SQS -> LAMBDA -> S3 + PARSE SNS

PARSE SNS -> PARSE SQS -> LAMBDA -> FETCH EVENT -> FETCH SNS
                                 -> PARSE  OBJ  ->    ???    -> PROFIT
```

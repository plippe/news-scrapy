package com.github.plippe.news.scrapy.mocks

import org.http4s.client.Client

class Http4sClientMock[F[_]] extends Client[F] {
    def expect[A](s: String)(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expect[A](uri: org.http4s.Uri)(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expect[A](req: F[org.http4s.Request[F]])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expect[A](req: org.http4s.Request[F])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expectOr[A](s: String)(onError: org.http4s.Response[F] => F[Throwable])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expectOr[A](uri: org.http4s.Uri)(onError: org.http4s.Response[F] => F[Throwable])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expectOr[A](req: F[org.http4s.Request[F]])(onError: org.http4s.Response[F] => F[Throwable])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def expectOr[A](req: org.http4s.Request[F])(onError: org.http4s.Response[F] => F[Throwable])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def fetch[A](req: F[org.http4s.Request[F]])(f: org.http4s.Response[F] => F[A]): F[A] = ???
    def fetch[A](req: org.http4s.Request[F])(f: org.http4s.Response[F] => F[A]): F[A] = ???
    def fetchAs[A](req: F[org.http4s.Request[F]])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def fetchAs[A](req: org.http4s.Request[F])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def get[A](s: String)(f: org.http4s.Response[F] => F[A]): F[A] = ???
    def get[A](uri: org.http4s.Uri)(f: org.http4s.Response[F] => F[A]): F[A] = ???
    def getAs[A](s: String)(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def getAs[A](uri: org.http4s.Uri)(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def prepAs[T](req: F[org.http4s.Request[F]])(implicit d: org.http4s.EntityDecoder[F,T]): F[T] = ???
    def prepAs[A](req: org.http4s.Request[F])(implicit d: org.http4s.EntityDecoder[F,A]): F[A] = ???
    def run(req: org.http4s.Request[F]): cats.effect.Resource[F,org.http4s.Response[F]] = ???
    def status(req: F[org.http4s.Request[F]]): F[org.http4s.Status] = ???
    def status(req: org.http4s.Request[F]): F[org.http4s.Status] = ???
    def stream(req: org.http4s.Request[F]): fs2.Stream[F,org.http4s.Response[F]] = ???
    def streaming[A](req: F[org.http4s.Request[F]])(f: org.http4s.Response[F] => fs2.Stream[F,A]): fs2.Stream[F,A] = ???
    def streaming[A](req: org.http4s.Request[F])(f: org.http4s.Response[F] => fs2.Stream[F,A]): fs2.Stream[F,A] = ???
    def successful(req: F[org.http4s.Request[F]]): F[Boolean] = ???
    def successful(req: org.http4s.Request[F]): F[Boolean] = ???
    def toHttpApp: org.http4s.HttpApp[F] = ???
    @deprecated("","")
    def toHttpService: org.http4s.HttpService[F] = ???
    def toKleisli[A](f: org.http4s.Response[F] => F[A]): cats.data.Kleisli[F,org.http4s.Request[F],A] = ???
    @deprecated("","")
    def toService[A](f: org.http4s.Response[F] => F[A]): org.http4s.Service[F,org.http4s.Request[F],A] = ???
}

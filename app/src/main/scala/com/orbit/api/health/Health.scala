package com.orbit.api.health

import cats.data.Kleisli
import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router

object Health {
  private val helloWorldService = HttpRoutes.of[IO] {
    case GET -> Root / "health"  =>
      Ok(s"Healthy Orbit!")
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
    case GET -> Root / "goodbye" / name =>
      Ok(s"Goodbye, $name.")
  }
  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router("/" -> helloWorldService).orNotFound
}

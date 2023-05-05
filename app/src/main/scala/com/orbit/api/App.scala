package com.orbit.api


import cats.effect._
import com.comcast.ip4s._
import com.orbit.api.health.Health
import org.http4s.ember.server._


object App extends IOApp.Simple {

  override def run(): IO[Unit] = {
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(Health.httpApp)
      .build
      .use(_ => IO.never)
  }
}

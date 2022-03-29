package com.example

import com.example.Debug._
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Blocking extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = withBlocker.as(ExitCode.Success)

  def withBlocker: IO[Unit] =
    for {
      _ <- IO("on default").debug
      _ <- IO.blocking(println("on blocker")).debug // this is going to do some "blocking work" on some other special thread
      _ <- IO("where am I?").debug // some other effect that runs after the blocker
    } yield ()
}

package com.example

import cats.effect.kernel.Resource
import com.example.Debug.*
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits.*

object BasicResource extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    stringResource.use(s =>
      IO(s"$s is so cool").debug
    ).as(ExitCode.Success)

  val stringResource: Resource[IO, String] =
    Resource.make(IO("> aquiring stringResource").debug *> IO("String")
      )(_ => IO("< releasing stringResource").debug.void)
}

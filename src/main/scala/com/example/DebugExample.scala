package com.example

import com.example.Debug.DebugHelper
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object DebugExample extends IOApp {
  /*
  Runs sequentially unless if we use mapN vs parMapN

  mapN
  [io-compute-4] hello
  [io-compute-4] world
  [io-compute-4] hello, world

  parMapN
  [io-compute-4] hello
  [io-compute-6] world
  [io-compute-5] hello, world
  */
  def run(args: List[String]): IO[ExitCode] = seq.as(ExitCode.Success)

  val hello = IO("hello").debug
  val world = IO("world").debug

  val seq = (hello, world)
    .parMapN((h ,w) => s"$h, $w")
    .debug
}

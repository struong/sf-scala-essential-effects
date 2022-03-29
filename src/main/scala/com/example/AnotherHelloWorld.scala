package com.example

import cats.effect.IOApp
import cats.effect.IO
import cats.effect.ExitCode

object AnotherHelloWorld extends IOApp {
  def run(args: List[String]): IO[ExitCode] = 
      helloWorld.as(ExitCode.Success)

  val helloWorld: IO[Unit] =
      for {
          _ <- putStr("Hello")
          _ <- putStr("World")
      } yield ()

  def putStr(s: => String): IO[Unit] = IO(println(s))
}

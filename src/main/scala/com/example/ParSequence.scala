package com.example

import com.example.Debug._
import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp}

object ParSequence extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    tasks
      .parSequence
      .debug
      .as(ExitCode.Success)

  val numTasks = 100

  // Sequence is where you have a list of effects and turn it into one effect with a list of results
  val tasks: List[IO[Int]] = List.tabulate(numTasks)(task)

  // some dumb function that does one task at a time
  def task(id: Int): IO[Int] = IO(id).debug
}

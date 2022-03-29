package com.example

import com.example.Debug._
import cats.implicits._
import cats.effect.{ExitCode, IO, IOApp}

object ParTraverse extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    tasks
      .parTraverse(task)
      .debug
      .as(ExitCode.Success)

  val numTasks = 100
  val tasks: List[Int] = List.range(0, numTasks)

  // some dumb function that does one task at a time
  def task(id: Int): IO[Int] = IO(id).debug
}

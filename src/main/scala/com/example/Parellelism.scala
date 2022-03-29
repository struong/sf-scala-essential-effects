package com.example

import com.example.Debug._
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object Parellelism extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      _ <- IO(s"number of CPUs: $numCpus")
      _ <- tasks.debug
    } yield ExitCode.Success

  val numCpus = Runtime.getRuntime().availableProcessors()

  // assign more work than the number of CPUs available
  val tasks = List.range(0, numCpus * 2).parTraverse(task)

  def task(i: Int): IO[Int] = IO(i).debug
}

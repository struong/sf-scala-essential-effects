package com.example

import com.example.Debug._
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object ParMapNErrors extends IOApp {

  // attempt is an error handling combinator
  // Usually if there's an exception or error raised for an IO
  // all execution will stop
  // with attempt, will get an Either[Error, A]
  // *>  is fa.flatMap(_ => fb) e.g. "process the original computation, and replace the result with whatever is given in the second argument"
  def run(args: List[String]): IO[ExitCode] =
    e1.attempt.debug *>
      IO("---").debug *>
      e2.attempt.debug *>
      IO("---").debug *>
      e3.attempt.debug *>
      IO.pure(ExitCode.Success)

  val ok = IO("hi").debug
  // Lifting an exception to an IO using raiseError
  // would have produced a string but actually produced an exception
  val ko1 = IO.raiseError[String](new RuntimeException("oh!")).debug
  val ko2 = IO.raiseError[String](new RuntimeException("noes!")).debug

  val e1 = (ok, ko1).parMapN((_, _) => ())
  val e2 = (ko1, ok).parMapN((_, _) => ())
  val e3 = (ko1, ko2).parMapN((_, _) => ()) // non-deterministic in which error you will get first
}

package com.example

import cats.effect.IO

object Debug {
  implicit class DebugHelper[A](ioa: IO[A]) {
    // help for seeing parallelism
    def debug: IO[A] =
      for {
        a <- ioa
        threadName = Thread.currentThread().getName
        _ = println(s"[$threadName] $a")
      } yield a
  }
}

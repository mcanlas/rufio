package com.htmlism.rufio.cats.io.syntax

import weaver.SimpleIOSuite

import com.htmlism.rufio.core.Path

object CatsIoSyntaxSuite extends SimpleIOSuite {
  private lazy val out =
    cats.effect.std.Console.make

  test("Can create a temporary file") {
    for {
      f <- Path.createTemporaryFile

      _ <- out.println(f)

      b <- f.exists
    } yield expect(b)
  }
}

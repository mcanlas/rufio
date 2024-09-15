package com.htmlism.rufio.cats.io.syntax

import weaver.SimpleIOSuite

import com.htmlism.rufio.core.Path

object CatsIoSyntaxSuite extends SimpleIOSuite {
  test("Can create a temporary file") {
    for {
      f <- Path.createTemporaryFile

      _ <- cats.effect.std.Console.make.println(f)

      b <- f.exists
    } yield expect(b)
  }
}

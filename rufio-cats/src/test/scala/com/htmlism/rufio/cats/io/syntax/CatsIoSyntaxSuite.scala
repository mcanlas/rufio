package com.htmlism.rufio.cats.io.syntax

import cats.effect.IO
import weaver.SimpleIOSuite

object CatsIoSyntaxSuite extends SimpleIOSuite {
  test("") {
    IO {
      expect.eql(1, 1)
    }
  }
}

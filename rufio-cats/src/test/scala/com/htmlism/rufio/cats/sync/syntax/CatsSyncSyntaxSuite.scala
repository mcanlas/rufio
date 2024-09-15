package com.htmlism.rufio.cats.sync.syntax

import cats.effect.IO
import weaver.SimpleIOSuite

object CatsSyncSyntaxSuite extends SimpleIOSuite {
  test("") {
    IO {
      expect.eql(1, 1)
    }
  }
}

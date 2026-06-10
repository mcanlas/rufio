package com.htmlism.rufio.withcats

import weaver.SimpleIOSuite

import com.htmlism.rufio.cats.io.syntax.*
import com.htmlism.rufio.core.Path

object CatsFileOpsSuite extends SimpleIOSuite {
  test("contents aliases readString and preserves the exact contents") {
    val expected =
      "foo\nbar"

    for {
      path <- Path.createTemporaryFile
      file  = File(path)

      _ <- file.writeString(expected)

      read     <- file.readString
      contents <- file.contents
    } yield expect.eql(expected, read) and expect.eql(read, contents)
  }

  test("writeLine ends the string with a newline") {
    val line =
      "foo"

    for {
      path <- Path.createTemporaryFile
      file  = File(path)

      _ <- file.writeLine(line)

      actual <- file.contents
    } yield expect.eql(line + System.lineSeparator, actual)
  }
}

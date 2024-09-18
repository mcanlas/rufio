package com.htmlism.rufio.cats.sync.syntax

import java.nio.file.attribute.PosixFilePermission

import weaver.SimpleIOSuite

import com.htmlism.rufio.core.Path

object CatsSyncSyntaxSuite extends SimpleIOSuite {
  private lazy val out =
    cats.effect.std.Console.make

  test("Can create a temporary file") {
    for {
      f <- Path.createTemporaryFile

      _ <- out.println(f)

      b <- f.exists
    } yield expect(b)
  }

  test("Can create a temporary directory") {
    for {
      d <- Path.createTemporaryDirectory

      _ <- out.println(d)

      b <- d.exists
    } yield expect(b)
  }

  test("Can test if a file exists") {
    for {
      f <- Path.createTemporaryFile

      _ <- out.println(f)

      b <- f.exists
    } yield expect(b)
  }

  test("Can test if a file is a directory") {
    for {
      f <- Path.createTemporaryFile

      _ <- out.println(f)

      b <- f.isDirectory
    } yield expect(!b)
  }

  test("Can write lines") {
    val expected =
      List("foo", "bar")

    for {
      f <- Path.createTemporaryFile

      _ <- f.writeLines(expected)

      xs <- f.readLines
    } yield expect.eql(expected, xs)
  }

  test("Can write a string") {
    val expected =
      "foo\nbar"

    for {
      f <- Path.createTemporaryFile

      _ <- f.writeString(expected)

      xs <- f.readLines
    } yield expect.eql(expected, xs.mkString("\n"))

  }

  test("Can read lines") {
    val expected =
      List("foo", "bar")

    for {
      f <- Path.createTemporaryFile

      _ <- f.writeLines(expected)

      xs <- f.readLines
    } yield expect.eql(expected, xs)
  }

  test("Can get file permissions") {
    for {
      f <- Path.createTemporaryFile

      xs <- f.getPosixFilePermissions
    } yield expect.eql(xs, Set(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE))
  }

  test("Can set file permissions") {
    for {
      f <- Path.createTemporaryFile

      _ <- f.setPosixFilePermissions(Set(PosixFilePermission.OWNER_WRITE))

      xs <- f.getPosixFilePermissions
    } yield expect.eql(xs, Set(PosixFilePermission.OWNER_WRITE))
  }

  test("Can add file permissions") {
    for {
      f <- Path.createTemporaryFile

      _ <- f.addPosixFilePermissions(Set(PosixFilePermission.OWNER_WRITE))

      xs <- f.getPosixFilePermissions
    } yield expect.eql(
      xs,
      Set(PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_WRITE)
    )
  }

  test("Can create a file") {
    val fileName =
      util.Random.alphanumeric.take(10).mkString + ".tmp"

    for {
      p <- Path.of("/tmp", fileName).create

      _ <- out.println(s"Touch at ${p.toString}")

      b <- p.exists
    } yield expect(b)
  }

  pureTest("Imports pure path name syntax") {
    expect.eql("abc", Path.of("abc").name)
  }
}

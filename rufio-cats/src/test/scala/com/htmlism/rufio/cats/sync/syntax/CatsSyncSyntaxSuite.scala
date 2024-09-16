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

  test("Can test if a file exists") {
    for {
      f <- Path.createTemporaryFile

      _ <- out.println(f)

      b <- f.exists
    } yield expect(b)
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
}

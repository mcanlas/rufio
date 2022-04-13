package com.htmlism.rufio

import cats.effect._
import cats.syntax.all._

import java.nio.charset.Charset
import java.nio.file.Files

package object withcats {
  type File =
    core.File

  implicit class FileOps[F[_]](f: File)(implicit F: Sync[F]) {
    def contents: F[String] =
      F.delay {
        Files.readAllBytes(f.path)
      }.map(xs => new String(xs, Charset.defaultCharset()))
  }
}

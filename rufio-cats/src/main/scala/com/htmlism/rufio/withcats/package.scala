package com.htmlism.rufio

import java.nio.charset.Charset
import java.nio.file.Files

import cats.effect._
import cats.syntax.all._

package object withcats {
  type File =
    core.File

  val File =
    core.File

  implicit class FileOps[F[_]](f: File)(implicit F: Sync[F]) {
    def contents: F[String] =
      F.delay {
        Files.readAllBytes(f.path)
      }.map(xs => new String(xs, Charset.defaultCharset()))
  }
}

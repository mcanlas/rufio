package com.htmlism.rufio

import java.nio.file.Files

import scala.jdk.CollectionConverters._

import cats.effect._
import cats.syntax.all._

package object withcats {
  type File =
    core.File

  val File =
    core.File

  implicit class CatsFileOps[F[_]](f: File)(implicit F: Sync[F]) extends core.FileOps[F] {
    def contents: F[String] =
      F.delay {
        Files
          .readString(f.path)
      }

    def getLines: F[List[String]] =
      F.delay {
        (Files
          .readAllLines(f.path): java.lang.Iterable[String])
          .asScala
          .toList
      }

    def write(s: String): F[Unit] =
      F.delay {
        Files
          .write(f.path, s.getBytes)
      }.void
  }
}

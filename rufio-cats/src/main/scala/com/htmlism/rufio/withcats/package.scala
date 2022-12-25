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

    def writeString(s: String): F[Unit] =
      F.delay {
        Files
          .write(f.path, List(s).asJava)
      }.void

    def writeLines(xs: Iterable[String]): F[Unit] =
      F.delay {
        Files
          .write(f.path, xs.asJava)
      }.void

    def exists: F[Boolean] =
      F.delay {
        Files
          .exists(f.path)
      }
  }
}

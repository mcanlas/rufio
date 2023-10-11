package com.htmlism.rufio

import java.nio.file.Files

import scala.jdk.CollectionConverters.*

import cats.effect.*
import cats.syntax.all.*

package object withcats {
  type File =
    core.File

  val File =
    core.File

  implicit class CatsFileOps[F[_]](f: File)(implicit F: Sync[F]) extends core.FileOps[F] {
    def contents: F[String] =
      F.blocking {
        Files
          .readString(f.path)
      }

    def getLines: F[List[String]] =
      F.blocking {
        (Files
          .readAllLines(f.path): java.lang.Iterable[String])
          .asScala
          .toList
      }

    def writeString(s: String): F[Unit] =
      F.blocking {
        Files
          .write(f.path, List(s).asJava)
      }.void

    def writeLines(xs: Iterable[String]): F[Unit] =
      F.blocking {
        Files
          .write(f.path, xs.asJava)
      }.void

    def exists: F[Boolean] =
      F.blocking {
        Files
          .exists(f.path)
      }
  }
}

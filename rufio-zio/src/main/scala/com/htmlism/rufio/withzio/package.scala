package com.htmlism.rufio

import java.nio.file.Files

import scala.jdk.CollectionConverters._

import zio._

package object withzio {
  type File =
    core.File

  val File =
    core.File

  implicit class ZioFileOps(f: File) extends core.FileOps[Task] {
    def contents: Task[String] =
      ZIO.attempt {
        Files
          .readString(f.path)
      }

    def getLines: Task[List[String]] =
      ZIO.attempt {
        (Files
          .readAllLines(f.path): java.lang.Iterable[String])
          .asScala
          .toList
      }

    def writeString(s: String): Task[Unit] =
      ZIO.attempt {
        Files
          .write(f.path, List(s).asJava)
      }.unit

    def writeLines(xs: Iterable[String]): Task[Unit] =
      ZIO.attempt {
        Files
          .write(f.path, xs.asJava)
      }.unit
  }
}

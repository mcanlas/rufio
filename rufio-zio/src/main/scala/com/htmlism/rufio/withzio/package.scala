package com.htmlism.rufio

import java.nio.file.Files

import scala.jdk.CollectionConverters.*

import zio.*

package object withzio {
  type File =
    core.File

  val File =
    core.File

  implicit class ZioFileOps(f: File) extends core.FileOps[Task] {
    def contents: Task[String] =
      ZIO.attemptBlocking {
        Files
          .readString(f.path)
      }

    def getLines: Task[List[String]] =
      ZIO.attemptBlocking {
        (Files
          .readAllLines(f.path): java.lang.Iterable[String])
          .asScala
          .toList
      }

    def writeString(s: String): Task[Unit] =
      ZIO.attemptBlocking {
        Files
          .write(f.path, List(s).asJava)
      }.unit

    def writeLines(xs: Iterable[String]): Task[Unit] =
      ZIO.attemptBlocking {
        Files
          .write(f.path, xs.asJava)
      }.unit

    def exists: Task[Boolean] =
      ZIO.attemptBlocking {
        Files
          .exists(f.path)
      }
  }
}

package com.htmlism.rufio.core

trait FileOps[F[_]] {

  /**
    * Get the contents of the file as one `String`
    */
  def contents: F[String]

  /**
    * Get the contents of the file, separated by newlines
    */
  def getLines: F[List[String]]

  /**
    * Write a `String` to a file, ending with a newline
    */
  def writeString(s: String): F[Unit]

  /**
    * Write lines to a file, ending each with a newline
    */
  def writeLines(xs: Iterable[String]): F[Unit]

  /**
    * Checks to see if the path already exists
    */
  def exists: F[Boolean]
}

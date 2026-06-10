package com.htmlism.rufio.core

trait FileOps[F[_]] {

  /**
    * Read the contents of the file as one `String`, preserving any trailing newline
    */
  def readString: F[String]

  /**
    * Alias for [[readString]]
    */
  def contents: F[String] =
    readString

  /**
    * Get the contents of the file, separated by newlines
    */
  def getLines: F[List[String]]

  /**
    * Write a `String` to a file exactly, without adding a newline
    */
  def writeString(s: String): F[Unit]

  /**
    * Write a `String` to a file, ending with a newline
    */
  def writeLine(s: String): F[Unit]

  /**
    * Write lines to a file, ending each with a newline
    */
  def writeLines(xs: Iterable[String]): F[Unit]

  /**
    * Checks to see if the path already exists
    */
  def exists: F[Boolean]
}

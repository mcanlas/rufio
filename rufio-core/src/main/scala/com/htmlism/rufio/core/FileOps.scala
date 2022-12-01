package com.htmlism.rufio.core

trait FileOps[F[_]] {
  def contents: F[String]

  def getLines: F[List[String]]
}

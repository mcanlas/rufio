package com.htmlism.rufio.core

trait CommonSyntax {
  implicit class PathOps(path: Path) {
    def name: String =
      path.getFileName.toString
  }
}

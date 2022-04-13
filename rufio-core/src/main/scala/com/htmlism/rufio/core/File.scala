package com.htmlism.rufio.core

import java.nio.file.Path

class File(path: Path)

object File {
  def apply(first: String, fragments: String*): File =
    new File(Path.of(first, fragments: _*))
}

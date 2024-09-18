package com.htmlism.rufio.core

import java.nio.file.Files

class PathCompanionOps[F[_]](thunker: Thunker[F]) {
  def createTemporaryFile: F[Path] =
    thunker.blocking {
      Files.createTempFile("", "")
    }

  def createTemporaryDirectory: F[Path] =
    thunker.blocking {
      Files.createTempDirectory("")
    }
}

package com.htmlism.rufio.core

import java.nio.file.Files

class PathCompanionOps[F[_]](thunker: Thunker[F]) {
  def createTemporaryFile: F[Path] =
    thunker.blocking {
      Files.createTempFile("", "")
    }

  /**
    * Creates a temporary file in a given directory
    */
  def createTemporaryFile(dir: Path): F[Path] =
    thunker.blocking {
      Files.createTempFile(dir, "", "")
    }

  def createTemporaryDirectory: F[Path] =
    thunker.blocking {
      Files.createTempDirectory("")
    }
}

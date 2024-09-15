package com.htmlism.rufio.core

import java.nio.file.Files

class PathInstanceOps[F[_]](path: Path, thunker: Thunker[F]) {
  def exists: F[Boolean] =
    thunker.delay {
      Files.exists(path)
    }
}

package com.htmlism.rufio.core

import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission

import scala.jdk.CollectionConverters.*

class PathInstanceOps[F[_]](path: Path, thunker: Thunker[F]) {
  def exists: F[Boolean] =
    thunker.blocking {
      Files.exists(path)
    }

  def getPosixFilePermissions: F[Set[PosixFilePermission]] =
    thunker.blocking {
      Files
        .getPosixFilePermissions(path)
        .asScala
        .toSet
    }

  // `add` equivalent does not exist here because it would require sequencing/FlatMap, not available for a non-cats F
  def setPosixFilePermissions(permissions: Set[PosixFilePermission]): F[Path] =
    thunker.blocking {
      Files
        .setPosixFilePermissions(path, permissions.asJava)
    }
}

package com.htmlism.rufio.cats.io

import cats.effect.IO

import com.htmlism.rufio.core.*

package object syntax {
  private lazy val ioThunker: Thunker[IO] =
    com.htmlism.rufio.cats.sync.syntax.syncThunker[IO]

  implicit class InstanceOpsIo(path: Path) extends PathInstanceOps(path, ioThunker)

  implicit class CompanionOpsIo(obj: Path.type) extends PathCompanionOps(ioThunker)
}

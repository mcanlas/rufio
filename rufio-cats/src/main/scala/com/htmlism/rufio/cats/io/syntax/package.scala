package com.htmlism.rufio.cats.io

import cats.effect.IO

import com.htmlism.rufio.core.*

package object syntax {
  private lazy val ioThunker: Thunker[IO] =
    new Thunker[IO] {
      def delay[A](x: A): IO[A] =
        IO.delay {
          x
        }
    }

  implicit class InstanceOpsIo(path: Path) extends PathInstanceOps(path, ioThunker)

  implicit class CompanionOpsIo(obj: Path.type) extends PathCompanionOps(ioThunker)
}

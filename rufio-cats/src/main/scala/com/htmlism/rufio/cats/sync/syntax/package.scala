package com.htmlism.rufio.cats.sync

import cats.effect.Sync

import com.htmlism.rufio.core.*

package object syntax {
  private def syncThunker[F[_]](implicit F: Sync[F]): Thunker[F] =
    new Thunker[F] {
      def delay[A](x: A): F[A] =
        F.blocking {
          x
        }
    }

  implicit class InstanceOpsSync[F[_]](path: Path)(implicit F: Sync[F]) extends PathInstanceOps(path, syncThunker(F))

  implicit class CompanionOpsSync[F[_]](obj: Path.type)(implicit F: Sync[F]) extends PathCompanionOps(syncThunker(F))
}

package com.htmlism.rufio.cats.sync

import java.nio.file.attribute.PosixFilePermission

import cats.effect.Sync
import cats.syntax.all.*

import com.htmlism.rufio.core.*

package object syntax {
  private[cats] def syncThunker[F[_]](implicit F: Sync[F]): Thunker[F] =
    new Thunker[F] {
      def blocking[A](x: A): F[A] =
        F.blocking {
          x
        }
    }

  implicit class InstanceOpsSync[F[_]](path: Path)(implicit F: Sync[F]) extends PathInstanceOps(path, syncThunker(F)) {
    def addPosixFilePermissions(permissions: Set[PosixFilePermission]): F[Path] =
      for {
        xs <- getPosixFilePermissions

        p <- setPosixFilePermissions(xs ++ permissions)
      } yield p
  }

  implicit class CompanionOpsSync[F[_]](obj: Path.type)(implicit F: Sync[F]) extends PathCompanionOps(syncThunker(F))
}

package com.htmlism.rufio.cats.sync

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission

import cats.effect.*
import cats.syntax.all.*

import com.htmlism.rufio.core.*

package object syntax extends CommonSyntax {
  private[cats] def syncThunker[F[_]](implicit F: Sync[F]): Thunker[F] =
    new Thunker[F] {
      def blocking[A](x: A): F[A] =
        F.blocking {
          x
        }
    }

  implicit class InstanceOpsSync[F[_]](path: Path)(implicit F: Sync[F])
      extends PathInstanceOps(path, syncThunker(using F)) {
    def addPosixFilePermissions(permissions: Set[PosixFilePermission]): F[Path] =
      for {
        xs <- getPosixFilePermissions

        p <- setPosixFilePermissions(xs ++ permissions)
      } yield p

    def inputStream: Resource[F, InputStream] =
      Resource.fromAutoCloseable(
        F.blocking(
          Files.newInputStream(path)
        )
      )
  }

  implicit class CompanionOpsSync[F[_]](obj: Path.type)(implicit F: Sync[F])
      extends PathCompanionOps(syncThunker(using F))
}

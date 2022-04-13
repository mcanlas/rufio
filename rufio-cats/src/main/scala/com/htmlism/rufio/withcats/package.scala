package com.htmlism.rufio

import cats.effect._

package object withcats {
  type File =
    core.File

  implicit class FileOps[F[_]](implicit F: Sync[F])
}

package com.htmlism.rufio

import cats.effect._

package object withcats {
  implicit class FileOps[F[_]](implicit F: Sync[F])
}

package com.htmlism.rufio.core

trait Thunker[F[_]] {
  def delay[A](x: A): F[A]
}

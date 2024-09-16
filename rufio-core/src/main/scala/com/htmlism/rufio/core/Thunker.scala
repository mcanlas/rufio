package com.htmlism.rufio.core

trait Thunker[F[_]] {
  def blocking[A](x: A): F[A]
}

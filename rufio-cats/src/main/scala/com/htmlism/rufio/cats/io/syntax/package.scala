package com.htmlism.rufio.cats.io

import cats.effect.IO

import com.htmlism.rufio.cats.sync.syntax.*
import com.htmlism.rufio.core.*

package object syntax {
  implicit class InstanceOpsIo(path: Path) extends InstanceOpsSync[IO](path)

  implicit class CompanionOpsIo(obj: Path.type) extends CompanionOpsSync[IO](obj)
}

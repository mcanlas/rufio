package com.htmlism.rufio.cats.io

import cats.effect.IO

import com.htmlism.rufio.cats.sync.syntax.*
import com.htmlism.rufio.core.*

package object syntax extends CommonSyntax {
  implicit class InstanceOpsIO(path: Path) extends InstanceOpsSync[IO](path)

  implicit class CompanionOpsIO(obj: Path.type) extends CompanionOpsSync[IO](obj)
}

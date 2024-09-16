package com.htmlism.rufio

import java.nio.file.Path as JPath

package object core {
  type Path =
    JPath

  object Path {
    def of(x: String, xs: String*): Path =
      JPath.of(x, xs*)
  }
}

lazy val rufio =
  project
    .in(file("."))
    .aggregate(core, cats, zio)
    .disablePublshing

lazy val core =
  module("core")

lazy val cats =
  module("cats")
    .withCats
    .dependsOn(core)

lazy val zio =
  module("zio")
    .withZio
    .dependsOn(core)

lazy val rufio =
  project
    .in(file("."))
    .aggregate(core, cats, zio)
    .disablePublishing

lazy val core =
  module("core")
    .settings(description := "A functional interface for file IO")

lazy val cats =
  module("cats")
    .settings(description := "A functional interface for file IO, with `cats-effect` integration")
    .withCats
    .dependsOn(core)

lazy val zio =
  module("zio")
    .settings(description := "A functional interface for file IO, with ZIO integration")
    .withZio
    .dependsOn(core)

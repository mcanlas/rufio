lazy val rufio =
  project
    .in(file("."))
    .aggregate(`rufio-core`, `rufio-cats`, `rufio-zio`)
    .disablePublshing

lazy val `rufio-core` =
  project

lazy val `rufio-cats` =
  project
    .withCats
    .dependsOn(`rufio-core`)

lazy val `rufio-zio` =
  project
    .withZio
    .dependsOn(`rufio-core`)

lazy val rufio =
  project
    .in(file("."))
    .dependsOn(`rufio-cats`, `rufio-zio`, `rufio-zio-rc`)

lazy val `rufio-cats` =
  project
    .withCats

lazy val `rufio-zio` =
  project
    .withZio

lazy val `rufio-zio-rc` =
  project
    .withZioRc

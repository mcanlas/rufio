lazy val rufio =
  project
    .in(file("."))
    .dependsOn(`rufio-cats`, `rufio-zio`)

lazy val `rufio-cats` =
  project

lazy val `rufio-zio` =
  project

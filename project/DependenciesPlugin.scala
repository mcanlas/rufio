import sbt.Keys.*
import sbt.*

object DependenciesPlugin extends AutoPlugin {
  override def trigger = allRequirements

  object autoImport {
    implicit class DependencyOps(p: Project) {
      def withCats: Project =
        p
          .settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "3.7.0")

      def withZio: Project =
        p
          .settings(libraryDependencies += "dev.zio" %% "zio" % "2.1.9")

      def withTesting: Project = {
        val weaverVersion =
          "0.10.0"

        p.settings(
          libraryDependencies ++= Seq(
            "org.typelevel" %% "weaver-cats"       % weaverVersion % Test,
            "org.typelevel" %% "weaver-scalacheck" % weaverVersion % Test
          )
        )
      }
    }
  }
}

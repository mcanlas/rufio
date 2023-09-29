import sbt.Keys._
import sbt._

/**
  * Automatically enriches projects with the following settings (despite the word "override").
  */
object ProjectPlugin extends AutoPlugin {

  /**
    * Defines what members will be imported to the `build.sbt` scope.
    */
  val autoImport = ThingsToAutoImport

  /**
    * Thus plug-in will automatically be enabled; it has no requirements.
    */
  override def trigger: PluginTrigger = AllRequirements

  object ThingsToAutoImport {
    private def jarName(s: String) =
      "rufio-" + s

    def module(s: String): Project =
      Project(s, file(jarName(s)))
        .settings(name := jarName(s))

    implicit class ProjectOps(p: Project) {
      def withCats: Project =
        p
          .settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "3.5.2")

      def withZio: Project =
        p
          .settings(libraryDependencies += "dev.zio" %% "zio" % "2.0.7")

      def withTesting: Project =
        p.settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.16" % "test")
    }
  }
}

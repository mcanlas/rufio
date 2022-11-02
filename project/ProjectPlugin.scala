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

    implicit class ProjectOps(p: Project) {
      def withCats: Project =
        p
          .settings(libraryDependencies += "org.typelevel" %% "cats-effect" % "3.3.14")

      def withZio: Project =
        p
          .settings(libraryDependencies += "dev.zio" %% "zio" % "1.0.13")

      def withZioRc: Project =
        p
          .settings(libraryDependencies += "dev.zio" %% "zio" % "2.0.0-RC5")

      def withTesting: Project =
        p.settings(libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test")
    }
  }
}

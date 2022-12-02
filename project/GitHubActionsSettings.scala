import sbt._
import sbt.Keys._
import sbtghactions._
import sbtghactions.GenerativeKeys._

/**
  * Automatically enriches projects with the following settings (despite the word "override").
  */
object GitHubActionsSettings extends AutoPlugin {

  /**
    * Thus plug-in will automatically be enabled; it has no requirements.
    */
  override def trigger: PluginTrigger = AllRequirements

  override def requires: Plugins =
    GitHubActionsPlugin

  override val buildSettings: Seq[Setting[_]] = Seq(
    organization               := "com.htmlism",
    versionScheme              := Some("strict"),
    publishTo                  := Some("GitHub" at "https://maven.pkg.github.com/mcanlas/rufio/"),
    credentials += Credentials(
      "GitHub Package Registry",
      "maven.pkg.github.com",
      "mcanlas",
      System.getenv("GITHUB_TOKEN")
    ),
    githubWorkflowBuild        := Seq(WorkflowStep.Sbt(List("scalafmtCheck", "test"))),
    githubWorkflowEnv          := Map("GITHUB_TOKEN" -> "${{ secrets.WRITE_PACKAGES_TOKEN }}"),
    githubWorkflowIncludeClean := false
  )
}

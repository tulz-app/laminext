import sbt.Def
import sbt.Keys.scalaVersion
import sbt.librarymanagement.CrossVersion

object DependencyVersions {

  val jsdom        = "16.4.0"
  val laminar      = "0.13.0"
  val `scala-test` = "3.2.9"
  val domtestutils = "0.14.8"
  val cats         = "2.6.1"
  val upickle      = "1.3.14"

  val circe: Def.Initialize[String] = Def.setting {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => "0.14.0-M7"
      case _            => "0.13.0"
    }
  }

  // -- website

  val sourcecode             = "0.2.7"
  val frontroute             = "0.13.1"
  val `embedded-files`       = "0.2.0"
  val `embedded-files-macro` = "0.2.4"
  val `scala-java-time`      = "2.3.0"

}

import sbt.Def
import sbt.Keys.scalaVersion
import sbt.librarymanagement.CrossVersion

object DependencyVersions {

  val jsdom        = "16.4.0"
  val laminar      = "0.13.0-M1"
  val `scala-test` = "3.2.7"
  val stringdiff   = "0.3.2"
  val domtestutils = "0.14.6"
  val cats         = "2.6.0"
  val zioJson      = "0.1.4"
  val upickle      = "1.3.11"

  val circe: Def.Initialize[String] = Def.setting {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => "0.14.0-M5"
      case _            => "0.13.0"
    }
  }

  // -- website

  val sourcecode             = "0.2.4"
  val frontroute             = "0.13.0-M1"
  val `embedded-files`       = "0.2.0"
  val `embedded-files-macro` = "0.2.1"
  val pprint                 = "0.6.0"
  val `scala-java-time`      = "2.2.0"

}

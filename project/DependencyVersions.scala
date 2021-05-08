import sbt.Def
import sbt.Keys.scalaVersion
import sbt.librarymanagement.CrossVersion

object DependencyVersions {

  val jsdom        = "16.4.0"
  val laminar      = "0.13.0"
  val `scala-test` = "3.2.8"
  val stringdiff   = "0.3.3"
  val domtestutils = "0.14.7"
  val cats         = "2.6.0"
  val upickle      = "1.3.12"

  val circe: Def.Initialize[String] = Def.setting {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => "0.14.0-M6"
      case _            => "0.13.0"
    }
  }

  // -- website

  val sourcecode             = "0.2.6"
  val frontroute             = "0.13.0"
  val `embedded-files`       = "0.2.0"
  val `embedded-files-macro` = "0.2.3"
  val pprint                 = "0.6.0"
  val `scala-java-time`      = "2.2.2"

}

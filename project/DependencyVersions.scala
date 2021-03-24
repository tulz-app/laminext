import sbt.Def
import sbt.Keys.scalaVersion
import sbt.librarymanagement.CrossVersion

object DependencyVersions {

  val jsdom                       = "16.4.0"
  val laminar                     = "0.12.1"
  val `scala-test`                = "3.2.3"
  val stringdiff                  = "0.3.1"
  val domtestutils                = "0.14.4"
  val cats                        = "2.4.2"
  val `cats-laws`                 = "2.4.2"
  val `discipline-munit`          = "1.0.6"
  val `scalacheck-shapeless_1.14` = "1.2.5"

  val circe: Def.Initialize[String] = Def.setting {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((3, _)) => "0.13.0"
      case _            => "0.13.0"
//      case Some((3, _)) => "0.14.0-M4"
    }
  }

  // -- website

  val sourcecode             = "0.2.4"
  val frontroute             = "0.12.2"
  val `embedded-files`       = "0.2.0"
  val `embedded-files-macro` = "0.2.1"
  val factorio               = "0.3.1"
  val pprint                 = "0.6.0"

}

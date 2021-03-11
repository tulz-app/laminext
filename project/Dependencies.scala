import dotty.tools.sbtplugin.DottyPlugin.autoImport._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import sbt._
import sbt.Keys._

object Dependencies {

  val laminar: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      ("com.raquo" %%% "laminar" % DependencyVersions.laminar).withDottyCompat(scalaVersion.value)
    )
  }
  val stringdiff: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      ("app.tulz" %%% "stringdiff" % DependencyVersions.stringdiff % Test).withDottyCompat(scalaVersion.value)
    )
  }

  val domtestutils: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      ("com.raquo" %%% "domtestutils" % DependencyVersions.domtestutils % Test).withDottyCompat(scalaVersion.value)
    )
  }

  val cats: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      ("org.typelevel" %%% "cats-core" % DependencyVersions.cats).withDottyCompat(scalaVersion.value)
    )
  }

  val circe: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      ("io.circe" %%% "circe-core"   % DependencyVersions.circe.value).withDottyCompat(scalaVersion.value),
      ("io.circe" %%% "circe-parser" % DependencyVersions.circe.value).withDottyCompat(scalaVersion.value)
    )
  }

  val frontroute: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "io.frontroute" %%% "frontroute" % DependencyVersions.frontroute
    )
  }

  val `embedded-files-macro`: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.yurique" %%% "embedded-files-macro" % DependencyVersions.`embedded-files-macro`
    )
  }

  val sourcecode: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.lihaoyi" %%% "sourcecode" % DependencyVersions.sourcecode
    )
  }

}

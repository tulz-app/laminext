import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

import sbt._

object Dependencies {

  val laminar: Def.Initialize[Seq[ModuleID]]    = Def.setting {
    Seq(
      "com.raquo" %%% "laminar" % DependencyVersions.laminar
    )
  }
  val stringdiff: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "app.tulz" %%% "stringdiff" % DependencyVersions.stringdiff % Test
    )
  }

  val domtestutils: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.raquo" %%% "domtestutils" % DependencyVersions.domtestutils % Test
    )
  }

  val cats: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "org.typelevel" %%% "cats-core" % DependencyVersions.cats
    )
  }

  val circe: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "io.circe" %%% "circe-core"   % DependencyVersions.circe,
      "io.circe" %%% "circe-parser" % DependencyVersions.circe
    )
  }

  val upickle: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "com.lihaoyi" %%% "upickle" % DependencyVersions.upickle
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

  val `scala-java-time`: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "io.github.cquiroz" %%% "scala-java-time" % DependencyVersions.`scala-java-time`
    )
  }

  val `scala-js-macrotask-executor`: Def.Initialize[Seq[ModuleID]] = Def.setting {
    Seq(
      "org.scala-js" %%% "scala-js-macrotask-executor" % DependencyVersions.`scala-js-macrotask-executor`
    )
  }

}

import sbt.CrossVersion
import sbt.Keys._

object ScalaOptions {

  val fixOptions = Seq(
    scalacOptions ++= (CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) =>
        Seq(
          "-feature",
          "-unchecked",
          "-language:existentials",
          "-language:experimental.macros",
          "-language:higherKinds",
          "-language:implicitConversions",
          "-Xlint:adapted-args",
          "-Xlint:constant",
          "-Xlint:delayedinit-select",
          "-Xlint:deprecation",
          "-Xlint:doc-detached",
          "-Xlint:implicit-recursion",
          "-Xlint:implicit-not-found",
          "-Xlint:inaccessible",
          "-Xlint:infer-any",
          "-Xlint:missing-interpolator",
          "-Xlint:nullary-unit",
          "-Xlint:option-implicit",
          "-Xlint:package-object-classes",
          "-Xlint:poly-implicit-overload",
          "-Xlint:private-shadow",
          "-Xlint:stars-align",
          "-Xlint:strict-unsealed-patmat",
          "-Xlint:type-parameter-shadow",
          "-Xlint:-byname-implicit",
          "-Wextra-implicit",
          "-Wnumeric-widen",
          "-Wvalue-discard",
          "-Wunused:nowarn",
          "-Wunused:locals",
          "-Wunused:patvars",
          "-Xfatal-warnings",
        )

      case Some((3, _)) =>
        Seq(
          "-deprecation",
          "-feature",
          "-unchecked",
          "-language:higherKinds",
          "-language:implicitConversions",
          "-Ykind-projector",
          "-Xfatal-warnings"
        )

      case _ => throw new RuntimeException(s"unexpected scalaVersion: ${scalaVersion.value}")

    })
  )

}

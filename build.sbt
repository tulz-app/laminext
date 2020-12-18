ThisBuild / organization := "app.tulz"
ThisBuild / homepage := Some(url("https://github.com/tulz-app/laminext"))
ThisBuild / licenses += ("MIT", url("https://github.com/tulz-app/laminext/blob/main/LICENSE.md"))
ThisBuild / developers := List(
  Developer(
    id = "yurique",
    name = "Iurii Malchenko",
    email = "i@yurique.com",
    url = url("https://github.com/yurique")
  )
)
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/tulz-app/tuplez"),
    "scm:git@github.com/tulz-app/laminext.git"
  )
)
ThisBuild / releasePublishArtifactsAction := PgpKeys.publishSigned.value
ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / pomIncludeRepository := { _ => false }
ThisBuild / sonatypeProfileName := "yurique"
ThisBuild / publishArtifact in Test := false
ThisBuild / publishMavenStyle := true
ThisBuild / releaseCrossBuild := true

lazy val noPublish = Seq(
  publishLocal / skip := true,
  publish / skip := true,
  publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo")))
)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.4",
  crossScalaVersions := Seq("2.13.4"),
  scalacOptions := Seq(
    "-unchecked",
    "-deprecation",
    "-feature",
    "-Xlint:nullary-unit,inaccessible,infer-any,missing-interpolator,private-shadow,type-parameter-shadow,poly-implicit-overload,option-implicit,delayedinit-select,stars-align",
    "-Xcheckinit",
    "-Ywarn-value-discard",
    "-language:implicitConversions",
    "-encoding",
    "utf8"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val baseDependencies = Seq(
  libraryDependencies ++= Seq(
    "com.raquo"   %%% "laminar" % "0.11.0",
    "com.lihaoyi" %%% "utest"   % "0.7.5" % Test
  )
)

lazy val commonDependencies = baseDependencies ++ Seq(
  libraryDependencies ++= Seq(
    "app.tulz" %%% "tuplez" % "0.2.0"
  )
)

lazy val catsDependencies = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %%% "cats-core" % "2.3.0"
  )
)

lazy val sttp3Dependencies = Seq(
  libraryDependencies ++= Seq(
    "com.softwaremill.sttp.client3" %%% "core" % "3.0.0-RC11"
  )
)

lazy val `laminext-core` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/core"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar extensions"
    )

lazy val `laminext-cats` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/cats"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(noPublish) // nothing here yet
    .settings(
      description := "Laminar utilities (cats)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-validation` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/validation"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(
      description := "Laminar utilities (validation)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-fsm` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/fsm"))
    .settings(commonSettings)
    .settings(
      description := "Laminar utilities (validation)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-sttp3` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/sttp3"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(sttp3Dependencies)
    .settings(
      description := "Laminar utilities (sttp3)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-markdown` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/markdown"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar utilities (markdown)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-videojs` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/videojs"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar utilities (markdown)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-tailwind` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/tailwind"))
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Misc utilities"
    ).dependsOn(`laminext-core`)

lazy val `laminext-util` =
  crossProject(JSPlatform)
    .crossType(CrossType.Pure)
    .in(file("modules/util"))
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(
      description := "Laminar utilities (tailwindcss)"
    )

lazy val base = project
  .in(file("."))
  .settings(noPublish)
  .aggregate(
    `laminext-core`.js,
    `laminext-validation`.js,
    `laminext-util`.js,
    `laminext-fsm`.js,
    `laminext-sttp3`.js,
    `laminext-videojs`.js,
    `laminext-markdown`.js,
    `laminext-tailwind`.js,
    `laminext-util`.js
  )

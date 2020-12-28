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
  requireJsDomEnv in Test := true,
  parallelExecution in Test := false,
  scalaJSUseMainModuleInitializer := true,
  scalaJSLinkerConfig in (Compile, fastLinkJS) ~= {
    _.withSourceMap(false)
  }
)

lazy val bundlerSettings = Seq(
//  jsEnv := new net.exoego.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
  installJsdom / version := "16.4.0"
)

lazy val baseDependencies = Seq(
  libraryDependencies ++= Seq(
    "com.raquo"     %%% "laminar"      % "0.11.0",
    "org.scalatest" %%% "scalatest"    % "3.2.3"  % Test,
    "app.tulz"      %%% "stringdiff"   % "0.3.1"  % Test,
    "com.raquo"     %%% "domtestutils" % "0.11.0" % Test
  )
)

lazy val commonDependencies = baseDependencies ++ Seq(
  libraryDependencies ++= Seq(
    "app.tulz" %%% "tuplez" % "0.2.0"
  )
)

lazy val catsDependencies = Seq(
  libraryDependencies ++= Seq(
    "org.typelevel" %%% "cats-core" % "2.3.1"
  )
)

lazy val sttp3Dependencies = Seq(
  libraryDependencies ++= Seq(
    "com.softwaremill.sttp.client3" %%% "core" % "3.0.0-RC13"
  )
)

val generateTupleCombinatorsFrom = 3
val generateTupleCombinatorsTo   = 22

lazy val `laminext-core` =
  project
    .in(file("modules/core"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      Compile / sourceGenerators += Def.task {
        Seq.concat(
          (generateTupleCombinatorsFrom to generateTupleCombinatorsTo).flatMap(n => new CombineSignalGenerator((Compile / sourceManaged).value, n).generate()),
          (generateTupleCombinatorsFrom - 1 to generateTupleCombinatorsTo).flatMap(n => new CombineEventStreamGenerator((Compile / sourceManaged).value, n).generate()),
          new SignalCombinesGenerator((Compile / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
          new EventStreamCombinesGenerator((Compile / sourceManaged).value, from = generateTupleCombinatorsFrom - 1, to = generateTupleCombinatorsTo).generate()
        )
      }.taskValue,
      //
      //Test / sourceGenerators += Def.task {
      //  Seq.concat(
      //    new CombineSignalTestGenerator((Test / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
      //    new CombineEventStreamTestGenerator((Test / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
      //    new CompositionTestGenerator((Test / sourceManaged).value).generate()
      //  )
      //}.taskValue
      mappings in (Compile, packageSrc) ++= {
        val base  = (sourceManaged in Compile).value
        val files = (managedSources in Compile).value
        files.map { f =>
          (f, f.relativeTo(base / "scala").get.getPath)
        }
      }
    )
    .settings(
      description := "Laminar extensions"
    )

lazy val `laminext-cats` =
  project
    .in(file("modules/cats"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(noPublish) // nothing here yet
    .settings(
      description := "Laminar utilities (cats)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-validation` =
  project
    .in(file("modules/validation"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(
      description := "Laminar utilities (validation)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-fsm` =
  project
    .in(file("modules/fsm"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(
      description := "Laminar utilities (validation)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-sttp3` =
  project
    .in(file("modules/sttp3"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(sttp3Dependencies)
    .settings(
      description := "Laminar utilities (sttp3)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-markdown` =
  project
    .in(file("modules/markdown"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar utilities (markdown)"
    ).dependsOn(`laminext-core`)

lazy val `laminext-videojs` =
  project
    .in(file("modules/videojs"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
//      npmDependencies in Test += "video.js" -> "7.10.2",
      description := "Laminar + video.js"
    ).dependsOn(`laminext-core`)

lazy val `laminext-ui` =
  project
    .in(file("modules/ui"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(bundlerSettings)
    .settings(
      description := "Laminar UI"
    ).dependsOn(`laminext-core`)

lazy val `laminext-tailwind` =
  project
    .in(file("modules/tailwind"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(bundlerSettings)
    .settings(
      useYarn := true
    )
    .settings(
      description := "Laminar UI (tailwindcss)"
    ).dependsOn(`laminext-core`, `laminext-ui`)

lazy val `laminext-util` =
  project
    .in(file("modules/util"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(
      description := "Misc utilities"
    )

lazy val base = project
  .in(file("."))
  .settings(noPublish)
  .settings(
    name := "laminext"
  )
  .aggregate(
    `laminext-core`,
    `laminext-validation`,
    `laminext-util`,
    `laminext-fsm`,
    `laminext-sttp3`,
    `laminext-videojs`,
    `laminext-markdown`,
    `laminext-ui`,
    `laminext-tailwind`,
    `laminext-util`
  )

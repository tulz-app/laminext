ThisBuild / organization := "io.laminext"
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

lazy val adjustScalacOptions = { options: Seq[String] =>
  options.filterNot(
    Set(
      "-Wdead-code",
      "-Wunused:implicits",
      "-Wunused:explicits",
      "-Wunused:imports",
      "-Wunused:params"
    )
  )
}

lazy val basicSettings = Seq(
  scalaVersion := "2.13.4",
  crossScalaVersions := Seq("2.13.4"),
  scalacOptions ~= adjustScalacOptions
)

lazy val commonSettings = basicSettings ++ Seq(
  requireJsDomEnv in Test := true,
  parallelExecution in Test := false,
  scalaJSUseMainModuleInitializer := true,
  scalaJSLinkerConfig in (Compile, fastLinkJS) ~= {
    _.withSourceMap(false)
  }
)

lazy val macrosSettings = Seq(
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val bundlerSettings = Seq(
  jsEnv := new net.exoego.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
  installJsdom / version := "16.4.0",
  useYarn := true
)

lazy val baseDependencies = Seq(
  libraryDependencies ++= Seq(
    "com.raquo"     %%% "laminar"      % "0.11.1-SNAPSHOT",
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

lazy val `core` =
  project
    .in(file("modules/core"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
//      Compile / sourceGenerators += Def.task {
//        Seq.concat(
//          (generateTupleCombinatorsFrom to generateTupleCombinatorsTo).flatMap(n => new CombineSignalGenerator((Compile / sourceManaged).value, n).generate()),
//          (generateTupleCombinatorsFrom - 1 to generateTupleCombinatorsTo).flatMap(n =>
//            new CombineEventStreamGenerator((Compile / sourceManaged).value, n).generate()
//          ),
//          new SignalCombinesGenerator((Compile / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
//          new EventStreamCombinesGenerator((Compile / sourceManaged).value, from = generateTupleCombinatorsFrom - 1, to = generateTupleCombinatorsTo).generate()
//        )
//      }.taskValue,
      //
      //Test / sourceGenerators += Def.task {
      //  Seq.concat(
      //    new CombineSignalTestGenerator((Test / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
      //    new CombineEventStreamTestGenerator((Test / sourceManaged).value, from = generateTupleCombinatorsFrom, to = generateTupleCombinatorsTo).generate(),
      //    new CompositionTestGenerator((Test / sourceManaged).value).generate()
      //  )
      //}.taskValue
//      mappings in (Compile, packageSrc) ++= {
//        val base  = (sourceManaged in Compile).value
//        val files = (managedSources in Compile).value
//        files.map { f =>
//          (f, f.relativeTo(base / "scala").get.getPath)
//        }
//      }
    )
    .settings(
      description := "Laminar extensions"
    )

lazy val `cats` =
  project
    .in(file("modules/cats"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(noPublish) // nothing here yet
    .settings(
      description := "Laminar utilities (cats)"
    ).dependsOn(`core`)

lazy val `validation` =
  project
    .in(file("modules/validation"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(catsDependencies)
    .settings(
      description := "Laminar utilities (validation)"
    ).dependsOn(`core`)

lazy val `fsm` =
  project
    .in(file("modules/fsm"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(
      description := "Laminar utilities (validation)"
    )

lazy val `sttp3` =
  project
    .in(file("modules/sttp3"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(sttp3Dependencies)
    .settings(
      description := "Laminar utilities (sttp3)"
    ).dependsOn(`core`)

lazy val `markdown` =
  project
    .in(file("modules/markdown"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar utilities (markdown)"
    )

lazy val `videojs` =
  project
    .in(file("modules/videojs"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar + video.js"
    ).dependsOn(`core`)

lazy val `highlight` =
  project
    .in(file("modules/highlight"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(
      description := "Laminar + highlight.js"
    )

lazy val `ui` =
  project
    .in(file("modules/ui"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(bundlerSettings)
    .settings(
      description := "Laminar UI"
    ).dependsOn(`core`)

lazy val `tailwind` =
  project
    .in(file("modules/tailwind"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(commonDependencies)
    .settings(bundlerSettings)
    .settings(
      description := "Laminar UI (tailwindcss)"
    ).dependsOn(`core`, `ui`)

lazy val `websocket` =
  project
    .in(file("modules/websocket"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(
      description := "Laminar websockets"
    ).dependsOn(`core`)

lazy val `util` =
  project
    .in(file("modules/util"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(
      description := "Misc utilities"
    )

lazy val website = project
  .in(file("website/site"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(EmbeddedFilesPlugin)
  .settings(basicSettings)
  .settings(noPublish)
  .settings(
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
    libraryDependencies ++= Seq(
      "com.lihaoyi"   %%% "sourcecode"           % "0.2.1",
      "com.raquo"     %%% "laminar"              % "0.11.0",
      "io.frontroute" %%% "frontroute"           % "0.11.5",
      "com.yurique"   %%% "embedded-files-macro" % "0.1.0",
      "io.mwielocha"  %%% "factorio-core"        % "0.3.1",
      "io.mwielocha"  %%% "factorio-annotations" % "0.3.1",
      "io.mwielocha"  %%% "factorio-macro"       % "0.3.1" % "provided"
    )
  )
  .settings(
    embedTextGlobs := Seq("**/*.md"),
    embedDirectories ++= (Compile / unmanagedSourceDirectories).value,
    (Compile / sourceGenerators) += embedFiles
  )
  .settings(
    addCompilerPlugin(("org.typelevel" %% "kind-projector" % "0.11.2").cross(CrossVersion.full)),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
  )
  .dependsOn(`core`, `validation`, `util`, `fsm`, `videojs`, `highlight`, `markdown`, `ui`, `tailwind`, `websocket`)

lazy val root = project
  .in(file("."))
  .settings(noPublish)
  .settings(
    name := "laminext"
  )
  .aggregate(
    `core`,
    `validation`,
    `util`,
    `fsm`,
    `sttp3`,
    `videojs`,
    `highlight`,
    `markdown`,
    `ui`,
    `tailwind`,
  )

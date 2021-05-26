inThisBuild(
  List(
    organization := "io.laminext",
    homepage := Some(url("https://github.com/tulz-app/laminext")),
    licenses := List("MIT" -> url("https://github.com/tulz-app/laminext/blob/main/LICENSE.md")),
    scmInfo := Some(ScmInfo(url("https://github.com/tulz-app/tuplez"), "scm:git@github.com/tulz-app/laminext.git")),
    developers := List(Developer("yurique", "Iurii Malchenko", "i@yurique.com", url("https://github.com/yurique"))),
    scalaVersion := ScalaVersions.v213,
    description := "Laminar utilities and components",
    crossScalaVersions := Seq(
      ScalaVersions.v213,
      ScalaVersions.v3
    ),
    Test / publishArtifact := false,
    Test / parallelExecution := false,
    githubWorkflowJavaVersions := Seq("openjdk@1.11.0"),
    githubWorkflowTargetTags ++= Seq("v*"),
    githubWorkflowPublishTargetBranches := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
    githubWorkflowPublish := Seq(WorkflowStep.Sbt(List("ci-release"))),
    githubWorkflowBuild := Seq(WorkflowStep.Sbt(List("test", "website/fastLinkJS"))),
    githubWorkflowEnv ~= (_ ++ Map(
      "PGP_PASSPHRASE"    -> s"$${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET"        -> s"$${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> s"$${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> s"$${{ secrets.SONATYPE_USERNAME }}"
    )),
    versionScheme := Some("early-semver") // Early Semantic Versioning that would keep binary compatibility across patch updates within 0.Y.z
  )
)

lazy val commonSettings = Seq.concat(
  ScalaOptions.fixOptions
)

lazy val bundlerSettings = Seq(
  jsEnv := new net.exoego.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
  installJsdom / version := DependencyVersions.jsdom,
  Test / requireJsDomEnv := true,
  useYarn := true
)

lazy val baseDependencies = Seq(
  libraryDependencies ++= Seq.concat(
    Dependencies.laminar.value,
    Dependencies.domtestutils.value
  )
)

lazy val catsDependencies = Seq(
  libraryDependencies ++= Seq.concat(
    Dependencies.cats.value
  )
)

lazy val circeDependencies = Seq(
  libraryDependencies ++= Seq.concat(
    Dependencies.circe.value
  )
)

lazy val upickleDependencies = Seq(
  libraryDependencies ++= Seq.concat(
    Dependencies.upickle.value
  )
)

lazy val base =
  project
    .in(file("modules/base"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `core` =
  project
    .in(file("modules/core"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `validation-core` =
  project
    .in(file("modules/validation-core"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .dependsOn(`base`, `core`)

lazy val `validation-cats` =
  project
    .in(file("modules/validation-cats"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(catsDependencies)
    .dependsOn(`validation-core`)

lazy val `fsm` =
  project
    .in(file("modules/fsm"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `markdown` =
  project
    .in(file("modules/markdown"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `videojs` =
  project
    .in(file("modules/videojs"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .dependsOn(`core`)

lazy val `highlight` =
  project
    .in(file("modules/highlight"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `ui` =
  project
    .in(file("modules/ui"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(bundlerSettings)
    .settings(baseDependencies)
    .dependsOn(`base`, `core`, `validation-core`)

lazy val `tailwind` =
  project
    .in(file("modules/tailwind"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(bundlerSettings)
    .settings(baseDependencies)
    .dependsOn(`base`, `core`, `ui`, `validation-core`)

lazy val `tailwind-default-theme` =
  project
    .in(file("modules/tailwind-default-theme"))
    .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
    .settings(commonSettings)
    .settings(bundlerSettings)
    .settings(baseDependencies)
    .dependsOn(`tailwind`)

lazy val `websocket` =
  project
    .in(file("modules/websocket"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `websocket-circe` =
  project
    .in(file("modules/websocket-circe"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(circeDependencies)
    .dependsOn(`websocket`)

lazy val `websocket-upickle` =
  project
    .in(file("modules/websocket-upickle"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(upickleDependencies)
    .dependsOn(`websocket`)

lazy val `fetch` =
  project
    .in(file("modules/fetch"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `fetch-circe` =
  project
    .in(file("modules/fetch-circe"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(circeDependencies)
    .dependsOn(`fetch`)

lazy val `fetch-upickle` =
  project
    .in(file("modules/fetch-upickle"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .settings(upickleDependencies)
    .dependsOn(`fetch`)

lazy val `util` =
  project
    .in(file("modules/util"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val website = project
  .in(file("website"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(EmbeddedFilesPlugin)
  .settings(commonSettings)
  .settings(noPublish)
  .settings(
    githubWorkflowTargetTags := Seq.empty,
    publish / skip := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSLinkerConfig ~= { _.withESFeatures(_.withUseECMAScript2015(false)) },
    Compile / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    scalaJSUseMainModuleInitializer := true,
    //    scalaJSLinkerConfig ~= (_.withModuleSplitStyle(org.scalajs.linker.interface.ModuleSplitStyle.FewestModules)),
    libraryDependencies ++= Seq.concat(
      Dependencies.laminar.value,
      Dependencies.frontroute.value,
      Dependencies.`embedded-files-macro`.value,
      Dependencies.sourcecode.value,
      Dependencies.`scala-java-time`.value,
    ),
    embedTextGlobs := Seq("**/*.md"),
    embedDirectories ++= (Compile / unmanagedSourceDirectories).value,
    (Compile / sourceGenerators) += embedFiles
  )
  .dependsOn(
    base,
    `core`,
    `validation-cats`,
    `util`,
    `fsm`,
    `videojs`,
    `highlight`,
    `markdown`,
    `ui`,
    `tailwind`,
    `tailwind-default-theme`,
    `websocket`,
    `websocket-circe`,
    `websocket-upickle`,
    `fetch`,
    `fetch-circe`,
    `fetch-upickle`
  )

lazy val noPublish = Seq(
  publishLocal / skip := true,
  publish / skip := true,
  publishTo := Some(Resolver.file("Unused transient repository", file("target/unusedrepo")))
)

lazy val root = project
  .in(file("."))
  .settings(noPublish)
  .settings(
    name := "laminext"
  )
  .aggregate(
    base,
    `core`,
    `validation-core`,
    `validation-cats`,
    `util`,
    `fsm`,
    `videojs`,
    `highlight`,
    `markdown`,
    `ui`,
    `tailwind`,
    `tailwind-default-theme`,
    `fetch`,
    `fetch-circe`,
    `fetch-upickle`,
    `websocket`,
    `websocket-circe`,
    `websocket-upickle`
  )

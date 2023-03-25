import org.scalajs.linker.interface.ESVersion

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.server.DriverFactory
import org.openqa.selenium.remote.server.DriverProvider

import org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv
import org.scalajs.jsenv.selenium.SeleniumJSEnv

import java.util.concurrent.TimeUnit

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

val disableWebsiteOnCI = true

val ciVariants = List("ciFirefox", "ciChrome", "ciJSDOMNodeJS")

lazy val useJSEnv =
  settingKey[JSEnv]("Use Node.js or a headless browser for running Scala.js tests")

addCommandAlias("ci", ciVariants.mkString("; ", "; ", ""))

addCommandAlias("ciFirefox", "; set Global / useJSEnv := JSEnv.Firefox; test; set Global / useJSEnv := JSEnv.NodeJS")
addCommandAlias("ciChrome", "; set Global / useJSEnv := JSEnv.Chrome; test; set Global / useJSEnv := JSEnv.NodeJS")
addCommandAlias("ciJSDOMNodeJS", "; set Global / useJSEnv := JSEnv.JSDOMNodeJS; test; set Global / useJSEnv := JSEnv.NodeJS")

Global / useJSEnv := JSEnv.NodeJS

inThisBuild(
  List(
    organization                               := "io.laminext",
    homepage                                   := Some(url("https://github.com/tulz-app/laminext")),
    licenses                                   := List("MIT" -> url("https://github.com/tulz-app/laminext/blob/main/LICENSE.md")),
    scmInfo                                    := Some(ScmInfo(url("https://github.com/tulz-app/tuplez"), "scm:git@github.com/tulz-app/laminext.git")),
    developers                                 := List(Developer("yurique", "Iurii Malchenko", "i@yurique.com", url("https://github.com/yurique"))),
    scalaVersion                               := ScalaVersions.v213,
    description                                := "Laminar utilities and components",
    crossScalaVersions                         := Seq(
      ScalaVersions.v213,
      ScalaVersions.v3
    ),
    Test / publishArtifact                     := false,
    Test / parallelExecution                   := false,
    scalafmtOnCompile                          := true,
    githubWorkflowJavaVersions                 := Seq(JavaSpec.temurin("17")),
    githubWorkflowUseSbtThinClient             := false,
    githubWorkflowSbtCommand                   := "sbt -mem 5000",
    githubWorkflowTargetTags ++= Seq("v*"),
    githubWorkflowArtifactUpload               := false,
    githubWorkflowPublishTargetBranches        := Seq(RefPredicate.StartsWith(Ref.Tag("v"))),
    githubWorkflowPublish                      := Seq(WorkflowStep.Sbt(List("ci-release"))),
    githubWorkflowBuild                        := Seq(
      WorkflowStep.Sbt(
        List("${{ matrix.ci }}")
      )
    ) ++ Seq(
      WorkflowStep.Sbt(
        List("website/compile"),
        name = Some("build website"),
        cond = Some("matrix.ci == 'ciJSDOMNodeJS'")
      )
    ).filterNot(_ => disableWebsiteOnCI),
    githubWorkflowEnv ~= (_ ++ Map(
      "PGP_PASSPHRASE"    -> s"$${{ secrets.PGP_PASSPHRASE }}",
      "PGP_SECRET"        -> s"$${{ secrets.PGP_SECRET }}",
      "SONATYPE_PASSWORD" -> s"$${{ secrets.SONATYPE_PASSWORD }}",
      "SONATYPE_USERNAME" -> s"$${{ secrets.SONATYPE_USERNAME }}"
    )),
    versionScheme                              := Some("semver-spec"), // all 0.y.z are treated as initial development (no bincompat guarantees)
    githubWorkflowBuildPreamble ++= Seq(
      WorkflowStep.Use(
        UseRef.Public("actions", "setup-node", "v3"),
        name = Some("Setup NodeJS v18 LTS"),
        params = Map("node-version" -> "18", "cache" -> "npm"),
        cond = Some("matrix.ci == 'ciJSDOMNodeJS'"),
      ),
      WorkflowStep.Run(
        List("npm install"),
        name = Some("Install jsdom"),
        cond = Some("matrix.ci == 'ciJSDOMNodeJS'")
      ),
    ),
    githubWorkflowBuildMatrixAdditions += "ci" -> ciVariants,
    Test / jsEnv                               := {
      import JSEnv._

      val old = (Test / jsEnv).value

      useJSEnv.value match {
        case NodeJS      => old
        case JSDOMNodeJS => new JSDOMNodeJSEnv()
        case Firefox     =>
          val profile = new FirefoxProfile()
          profile.setPreference("privacy.file_unique_origin", false)
          val options = new FirefoxOptions()
          options.setProfile(profile)
          options.setHeadless(true)
          new SeleniumJSEnv(options)
        case Chrome      =>
          val options = new ChromeOptions()
          options.setHeadless(true)
          options.addArguments("--allow-file-access-from-files")
          val factory = new DriverFactory {
            val defaultFactory = SeleniumJSEnv.Config().driverFactory

            def newInstance(capabilities: org.openqa.selenium.Capabilities): WebDriver = {
              val driver = defaultFactory.newInstance(capabilities).asInstanceOf[ChromeDriver]
              driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.HOURS)
              driver.manage().timeouts().setScriptTimeout(1, TimeUnit.HOURS)
              driver
            }

            def registerDriverProvider(provider: DriverProvider): Unit =
              defaultFactory.registerDriverProvider(provider)
          }
          new SeleniumJSEnv(options, SeleniumJSEnv.Config().withDriverFactory(factory))
      }
    }
  ),
)

lazy val commonSettings = Seq.concat(
  ScalaOptions.fixOptions,
  scalacOptions ++= {
    val sourcesGithubUrl  = s"https://raw.githubusercontent.com/tulz-app/laminext/${git.gitHeadCommit.value.get}/"
    val sourcesOptionName = CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, _)) => "-P:scalajs:mapSourceURI"
      case Some((3, _)) => "-scalajs-mapSourceURI"
      case _            => throw new RuntimeException(s"unexpected scalaVersion: ${scalaVersion.value}")
    }
    val moduleSourceRoot  = file("").toURI.toString
    Seq(
      s"$sourcesOptionName:$moduleSourceRoot->$sourcesGithubUrl"
    )
  }
)

lazy val baseDependencies = Seq(
  libraryDependencies ++= Seq.concat(
    Dependencies.laminar.value,
    Dependencies.stringdiff.value,
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
    .dependsOn(`core`, `base`)

lazy val `highlight` =
  project
    .in(file("modules/highlight"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)

lazy val `ui` =
  project
    .in(file("modules/ui"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .dependsOn(`base`, `core`, `validation-core`)

lazy val `tailwind-default-theme` =
  project
    .in(file("modules/tailwind-default-theme"))
    .enablePlugins(ScalaJSPlugin)
    .settings(commonSettings)
    .settings(baseDependencies)
    .dependsOn(`ui`)

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
    .dependsOn(`core`, `util`)

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

lazy val parser   = Parser.builder.build
lazy val renderer = HtmlRenderer.builder.build

lazy val laminextSiteVersion: String = IO.read(file("website/.laminext-version")).trim
lazy val thisVersionSitePrefix       = s"/v/$laminextSiteVersion/"

lazy val vars = Seq(
  "laminextVersion" -> "0.16.0-SNAPSHOT",
  "laminarVersion"  -> "15.0.1",
  "scalajsVersion"  -> "1.13.0",
  "scala3version"   -> "3.2.2",
)

def templateVars(s: String): String =
  vars.foldLeft(s) { case (acc, (varName, varValue)) =>
    acc.replace(s"{{${varName}}}", varValue)
  }

lazy val website = project
  .in(file("website"))
  .enablePlugins(ScalaJSPlugin, EmbeddedFilesPlugin, BuildInfoPlugin)
  .settings(commonSettings)
  .settings(noPublish)
  .settings(
    githubWorkflowTargetTags        := Seq.empty,
    publish / skip                  := true,
    buildInfoKeys                   := Seq[BuildInfoKey](
      version,
      scalaVersion,
      BuildInfoKey(
        "laminextSiteVersion" -> laminextSiteVersion
      )
    ),
    buildInfoPackage                := "io.laminext",
    Compile / fastLinkJS / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule) },
    Compile / fullLinkJS / scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    scalaJSLinkerConfig ~= { _.withESFeatures(_.withESVersion(ESVersion.ES5_1)) },
    Compile / scalaJSLinkerConfig ~= { _.withSourceMap(false) },
    scalaJSUseMainModuleInitializer := true,
    //    scalaJSLinkerConfig ~= (_.withModuleSplitStyle(org.scalajs.linker.interface.ModuleSplitStyle.FewestModules)),
    libraryDependencies ++= Seq.concat(
      Dependencies.laminar.value,
      Dependencies.frontroute.value,
      Dependencies.`embedded-files-macro`.value,
      Dependencies.sourcecode.value,
      Dependencies.`scala-java-time`.value,
      Dependencies.`scala-js-macrotask-executor`.value,
    ),
    embedTextGlobs                  := Seq("**/*.md"),
    embedDirectories ++= (Compile / unmanagedSourceDirectories).value,
    embedTransform                  := Seq(
      TransformConfig(
        when = _.getFileName.toString.endsWith(".md"),
        transform = { s =>
          templateVars(renderer.render(parser.parse(s)))
            .replace(
              """<a href="/""",
              s"""<a href="${thisVersionSitePrefix}"""
            )
        }
      )
    ),
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
  publish / skip      := true,
  publishTo           := Some(Resolver.file("Unused transient repository", file("target/unusedrepo")))
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
    `tailwind-default-theme`,
    `fetch`,
    `fetch-circe`,
    `fetch-upickle`,
    `websocket`,
    `websocket-circe`,
    `websocket-upickle`
  )

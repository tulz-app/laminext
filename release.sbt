ThisBuild / organization := "io.laminext"
ThisBuild / homepage := Some(url("https://github.com/tulz-app/laminext"))
ThisBuild / licenses += "MIT" -> url("https://github.com/tulz-app/laminext/blob/main/LICENSE.md")
ThisBuild / developers += Developer("yurique", "Iurii Malchenko", "i@yurique.com", url("https://github.com/yurique"))
ThisBuild / scmInfo := Some(ScmInfo(url("https://github.com/tulz-app/tuplez"), "scm:git@github.com/tulz-app/laminext.git"))
ThisBuild / publishTo := sonatypePublishToBundle.value
ThisBuild / sonatypeProfileName := "yurique"
ThisBuild / publishArtifact in Test := false

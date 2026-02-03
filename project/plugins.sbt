logLevel := Level.Warn

libraryDependencies += "org.commonmark" % "commonmark"               % "0.27.1"
libraryDependencies += "org.scala-js"  %% "scalajs-env-nodejs"       % "1.5.0"
libraryDependencies += "org.scala-js"  %% "scalajs-env-selenium"     % "1.1.1"
libraryDependencies += "org.scala-js"  %% "scalajs-env-jsdom-nodejs" % "1.1.1"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.20.2")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("org.typelevel" % "sbt-tpolecat" % "0.5.2")

addSbtPlugin("com.yurique" % "sbt-embedded-files" % "0.4.0")

addSbtPlugin("com.github.sbt" % "sbt-github-actions" % "0.29.0")

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.11.2")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.13.1")

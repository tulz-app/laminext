logLevel := Level.Warn

libraryDependencies += "org.commonmark" % "commonmark"               % "0.20.0"
libraryDependencies += "org.scala-js"  %% "scalajs-env-nodejs"       % "1.4.0"
libraryDependencies += "org.scala-js"  %% "scalajs-env-selenium"     % "1.1.1"
libraryDependencies += "org.scala-js"  %% "scalajs-env-jsdom-nodejs" % "1.1.0"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.14.0")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")

addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.2.1")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.21")

//addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.3.1")

addSbtPlugin("com.yurique" % "sbt-embedded-files" % "0.4.0")

addSbtPlugin("com.github.sbt" % "sbt-github-actions" % "0.15.0")

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.12")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.11.0")

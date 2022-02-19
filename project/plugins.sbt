logLevel := Level.Warn

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.7.1")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.1.0")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.10")

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0")

libraryDependencies += "net.exoego" %% "scalajs-env-jsdom-nodejs" % "2.1.0"

addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.22")

addSbtPlugin("com.yurique" % "sbt-embedded-files" % "0.2.2")

addSbtPlugin("com.codecommit" % "sbt-github-actions" % "0.13.0")

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.10")

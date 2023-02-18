logLevel := Level.Warn

libraryDependencies += "net.exoego" %% "scalajs-env-jsdom-nodejs" % "2.1.0"
libraryDependencies += "org.scala-js" %% "scalajs-env-nodejs" % "1.4.0"
libraryDependencies += "org.scala-js" %% "scalajs-env-selenium" % "1.1.1"
libraryDependencies += "org.scala-js" %% "scalajs-env-jsdom-nodejs" % "1.1.0"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.13.0")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.2.0")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.0")

addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.2.1")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.15")

//addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.3.1")

addSbtPlugin("com.yurique" % "sbt-embedded-files" % "0.2.3")

addSbtPlugin("com.codecommit" % "sbt-github-actions" % "0.14.2")

addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.11")

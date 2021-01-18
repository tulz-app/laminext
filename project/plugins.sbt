logLevel := Level.Warn

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.3.1")

addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject" % "1.0.0")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.0.2")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.5")

addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.20.0")

libraryDependencies += "net.exoego" %% "scalajs-env-jsdom-nodejs" % "2.0.0"

addSbtPlugin("io.github.davidgregory084" % "sbt-tpolecat" % "0.1.16")

addSbtPlugin("com.yurique" % "sbt-embedded-files" % "0.2.0")

addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.1")

addSbtPlugin("com.codecommit" % "sbt-github-actions" % "0.10.0")

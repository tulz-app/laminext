import sbt._

import sbt.Keys._

object ScalaOptions {
  val fixOptions = Seq(
    scalacOptions ~= (_.filterNot(
      Set(
        "-Wdead-code",
        "-Wunused:implicits",
        "-Wunused:explicits",
        "-Wunused:imports",
        "-Wunused:params",
        "-source",
        "future"
      )
    )),
    Compile / doc / scalacOptions ~= (_.filterNot(
      Set(
        "-scalajs",
        "-deprecation",
        "-explain-types",
        "-explain",
        "-feature",
        "-language:existentials,experimental.macros,higherKinds,implicitConversions",
        "-unchecked",
        "-Xfatal-warnings",
        "-Ykind-projector",
        "-from-tasty",
        "-encoding",
        "utf8",
      )
    ))
  )

}

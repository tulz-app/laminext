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
        "-Wunused:privates",
        "-source",
        "future"
      )
    ))
  )

}

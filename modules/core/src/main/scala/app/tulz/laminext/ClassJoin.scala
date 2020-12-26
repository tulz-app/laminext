package app.tulz.laminext

trait ClassJoin {

  def classJoin(classes: String*): String =
    classes.flatMap(_.split("\\s+")).distinct.mkString(" ")

}

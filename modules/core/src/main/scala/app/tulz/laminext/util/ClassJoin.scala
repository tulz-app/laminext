package app.tulz.laminext.util

trait ClassJoin {

  def classJoin(classes: String*): String =
    classes.flatMap(_.split("\\s+")).distinct.mkString(" ")

}

object ClassJoin extends ClassJoin

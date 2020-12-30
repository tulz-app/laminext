package io.laminext.util

object ClassJoin {

  def apply(classes: String*): String =
    classes.flatMap(_.split("\\s+")).distinct.mkString(" ")

}

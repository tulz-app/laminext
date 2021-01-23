package io.laminext.util

object ClassTokenize {

  def apply(className: String*): Seq[String] =
    if (className.forall(_.isEmpty)) {
      Seq.empty
    } else {
      className.flatMap(_.split(' ').map(_.replaceAll("\\s+", "")).filterNot(_.isEmpty)).distinct
    }

}

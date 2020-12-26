package app.tulz.laminext

trait ClassTokenize {

  def classTokenize(className: String*): Seq[String] = {
    if (className.forall(_.isEmpty)) {
      Seq.empty
    } else {
      className.flatMap(_.split(' ').map(_.replaceAll("\\s+", "")).filterNot(_.isEmpty)).distinct
    }
  }

}

object ClassTokenize extends ClassTokenize

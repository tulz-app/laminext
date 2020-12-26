package app.tulz.laminext

import app.tulz.laminext.ops.signal.SignalCompanionOps
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait SmartClass {

  def smartClass(
    classes: (String, Boolean)*
  ): Modifier[ReactiveHtmlElement.Base] =
    cls := classes.flatMap { case (classes, enable) => ClassTokenize.classTokenize(classes).map(_ -> enable) }

  def smartClasses(
    classes: (Seq[String], Signal[Boolean])*
  ): Modifier[ReactiveHtmlElement.Base] = {
    smartClass(
      SignalCompanionOps.seq(classes.map(_._2)).map { classesAndToggles => classes.map(_._1).zip(classesAndToggles) }
    )
  }

  def smartClass(
    classes: Signal[Seq[(Seq[String], Boolean)]]
  ): Modifier[ReactiveHtmlElement.Base] = {
    cls <-- classes
      .map { classesAndToggles =>
        val (enabledClasses, disabledClasses) =
          classesAndToggles.partitionMap { case (classes, enabled) => if (enabled) Left(classes) else Right(classes) }
        val enabledClassesDistinct  = enabledClasses.flatten.distinct
        val disabledClassesFiltered = disabledClasses.flatten.distinct.filterNot(enabledClassesDistinct.contains)
        enabledClassesDistinct.map(_ -> true) ++ disabledClassesFiltered.map(_ -> false)
      }
  }

}

package io.laminext
package util

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait SmartClass {

  def smartClass(
    classes: (String, Boolean)*
  ): Modifier[ReactiveHtmlElement.Base] =
    cls := classes.flatMap { case (classes, enable) =>
      ClassTokenize(classes).map(_ -> enable)
    }

  def smartClass(
    classes: Signal[Seq[(Seq[String], Boolean)]]
  ): Modifier[ReactiveHtmlElement.Base] =
    cls <-- classes
      .map { classesAndToggles =>

        val (enabledClasses, disabledClasses) =
          classesAndToggles.partitionMap { case (classes, enabled) =>
            if (enabled) Left(classes) else Right(classes)
          }

        val enabledClassesDistinct =
          enabledClasses.flatten.distinct

        val disabledClassesFiltered =
          disabledClasses.flatten.distinct.filterNot(enabledClassesDistinct.contains)

        enabledClassesDistinct.map(_ -> true) ++ disabledClassesFiltered.map(
          _ -> false
        )

      }

}

object SmartClass extends SmartClass

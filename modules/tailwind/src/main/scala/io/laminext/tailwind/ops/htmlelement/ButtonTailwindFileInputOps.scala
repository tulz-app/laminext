package io.laminext.tailwind
package ops.htmlelement

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import theme.Theme
import io.laminext.syntax.core._
import io.laminext.validation.ops.element.ValidatedElement
import org.scalajs.dom.raw.File
import org.scalajs.dom.raw.HTMLInputElement

import org.scalajs.dom

class ButtonTailwindFileInputOps[T <: dom.html.Button](el: ReactiveHtmlElement[T]) {

  @inline def fileInput[Err, Out](
    fileInput: ValidatedElement[HTMLInputElement, Seq[File], Err, Out],
    labelSelecting: Element = defaultLabelSelecting,
    labelReady: Seq[File] => Element = defaultLabelReady,
    thm: theme.FileInput = Theme.current.fileInput
  ): ReactiveHtmlElement[T] =
    el.amend(
      cls := "relative overflow-hidden",
      cls <-- fileInput.value.combineWithFn(fileInput.validatedValue) { (files, validated) =>
        Seq(
          thm.invalid.classes   -> (files.nonEmpty && validated.isLeft),
          thm.ready.classes     -> (files.nonEmpty && validated.isRight),
          thm.selecting.classes -> files.isEmpty
        )
      },
      child <-- fileInput.value.map { files =>
        if (files.nonEmpty) {
          labelReady(files)
        } else {
          labelSelecting
        }
      },
      fileInput.el.amend(
        cls := "absolute block top-0 right-0 min-w-full min-h-full opacity-0 outline-0 text-right bg-white",
        fontSize := "100px",
        cursor := "inherit",
      )
    )

  private def defaultLabelSelecting: Element = span("Select file", hellip)

  private def defaultLabelReady(files: Seq[File]): Element =
    if (files.size > 1) {
      span(s"${files.size} files selected")
    } else {
      span(files.headOption.flatMap(f => Option(f.name)).getOrElse("File selected"): String)
    }

}

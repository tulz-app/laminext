package io.laminext.tailwind
package fileinput

import syntax._
import theme.Theme
import io.laminext.syntax.all._
import com.raquo.domtypes.jsdom.defs.events.TypedTargetEvent
import com.raquo.laminar.api.L._
import com.raquo.laminar.api.L
import com.raquo.laminar.builders.HtmlTag
import org.scalajs.dom
import org.scalajs.dom.ext._
import org.scalajs.dom.raw.File
import org.scalajs.dom.raw.HTMLInputElement

object FileInput {

  def apply[A, B](
    tag: HtmlTag[dom.html.Element] = button.btn.md.build,
    validate: Seq[File] => Either[A, B],
    accept: String,
    multiple: Boolean = false,
    disabled: Signal[Boolean],
    labelSelecting: Element = defaultLabelSelecting,
    labelReady: Seq[File] => Element = defaultLabelReady,
    thm: theme.FileInput = Theme.current.fileInput
  ): (Element, Signal[Either[A, B]]) = {
    val filesVar = Var[(Seq[File], Either[A, B])](Seq.empty -> validate(Seq.empty))
    val changeHandler: TypedTargetEvent[dom.html.Element] => Unit = e => {
      val input = e.target.asInstanceOf[HTMLInputElement]
      val files = new EasySeq[File](input.files.length, input.files.apply).toSeq
      filesVar.writer.onNext(files -> validate(files))
    }

    val fileInput = input(
      `type` := "file",
      L.accept := accept,
      L.multiple := multiple,
      L.disabled <-- disabled,
      cls := "absolute block top-0 right-0 min-w-full min-h-full opacity-0 outline-0 text-right bg-white",
      fontSize := "100px",
      cursor := "inherit",
      onChange --> changeHandler
    )

    val element =
      tag(
        cls := "relative overflow-hidden",
        cls <-- filesVar.signal.map { case (files, validated) =>
          Seq(
            thm.invalid.classes   -> (files.nonEmpty && validated.isLeft),
            thm.ready.classes     -> (files.nonEmpty && validated.isRight),
            thm.selecting.classes -> files.isEmpty
          )
        },
        child <-- filesVar.signal.map { case (files, _) =>
          if (files.nonEmpty) {
            labelReady(files)
          } else {
            labelSelecting
          }
        },
        L.disabled <-- disabled,
        fileInput
      )

    (element, filesVar.signal.map(_._2))
  }

  private def defaultLabelSelecting: Element = span("Select file", hellip)

  private def defaultLabelReady(files: Seq[File]): Element =
    if (files.size > 1) {
      span(s"${files.size} files selected")
    } else {
      span(files.headOption.flatMap(f => Option(f.name)).getOrElse("File selected"): String)
    }

}

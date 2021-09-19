package io.laminext.ui

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.syntax.core.hellip
import io.laminext.syntax.validation._
import io.laminext.validation.components.ValidatedElement
import io.laminext.validation.Validation
import org.scalajs.dom.raw.File
import org.scalajs.dom.raw.HTMLInputElement

trait FileInputElementCreate {

  import FileInputElementCreate._

  def fileInput[Err](
    validation: Validation[File, Err, File],
    noFileError: Err,
    styling: FileInputElement.Styling,
    inputMods: Modifier[ReactiveHtmlElement[org.scalajs.dom.html.Input]] = emptyMod,
    labelSelecting: Element = defaultLabelSelecting,
    labelReady: Seq[File] => Element = defaultLabelReady
  ): FileInputElement[Err, File] = {
    val fileInput = input(tpe := "file", inputElementMods, inputMods).validatedFile(noFileError, validation)
    build(fileInput, styling, labelSelecting, labelReady)
  }

  def multiFileInput[Err](
    validation: Validation[File, Err, File],
    noFileError: Err,
    styling: FileInputElement.Styling,
    inputMods: Modifier[ReactiveHtmlElement[org.scalajs.dom.html.Input]] = emptyMod,
    labelSelecting: Element = defaultLabelSelecting,
    labelReady: Seq[File] => Element = defaultLabelReady
  ): FileInputElement[Either[Err, Seq[(File, Err)]], Seq[File]] = {
    val multiFileValidation: Validation[Seq[File], Either[Err, Seq[(File, Err)]], Seq[File]] =
      files => {
        if (files.isEmpty) {
          Left(Left(noFileError))
        } else {
          val validated = files.map(file => (file, validation(file)))
          val failures  = validated.collect { case (file, Left(err)) => (file, err) }
          if (failures.nonEmpty) {
            Left(Right(failures))
          } else {
            Right(validated.collect { case (_, Right(value)) => value })
          }
        }
      }
    val fileInput                                                                            = input(tpe := "file", multiple(true), inputElementMods, inputMods).validatedFiles(multiFileValidation)
    build(fileInput, styling, labelSelecting, labelReady)
  }

  private def build[Err, Out](
    fileInput: ValidatedElement[HTMLInputElement, Seq[File], Err, Out],
    styling: FileInputElement.Styling,
    labelSelecting: Element = defaultLabelSelecting,
    labelReady: Seq[File] => Element = defaultLabelReady
  ): FileInputElement[Err, Out] = {
    val $status = fileInput.value.combineWithFn(fileInput.validatedValue) { (files, validated) =>
      if (files.isEmpty) {
        FileInputElement.Status.Selecting
      } else {
        if (validated.isLeft) {
          FileInputElement.Status.Invalid
        } else {
          FileInputElement.Status.Ready
        }
      }
    }

    val el = button(
      child <-- fileInput.value.map { files =>
        if (files.nonEmpty) {
          labelReady(files)
        } else {
          labelSelecting
        }
      },
      fileInput.el,
      position.relative,
      overflow.hidden,
      styling($status),
    )

    new FileInputElement(
      el = el,
      value = fileInput.value,
      validatedValue = fileInput.validatedValue,
      validationError = fileInput.validationError
    )
  }

}

private[ui] object FileInputElementCreate {

  private val inputElementMods: Mod[HtmlElement] = Seq(
    position.absolute,
    display.block,
    top("0"),
    right("0"),
    minWidth("100%"),
    minHeight("100%"),
    opacity(0),
    outline("0"),
    textAlign.right,
    backgroundColor("white"),
    fontSize("100px"),
    cursor("inherit"),
  )

  private def defaultLabelSelecting: Element = span("Select file", hellip)

  private def defaultLabelReady(files: Seq[File]): Element =
    if (files.size > 1) {
      span(s"${files.size} files selected")
    } else {
      span(files.headOption.flatMap(f => Option(f.name)).getOrElse("File selected"): String)
    }
}

package io.laminext.site.examples.ui.ex_fileinput

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample
import io.laminext.syntax.ui._
import io.laminext.ui.theme.FileInputConfig

object FileInputExample
    extends CodeExample(
      id = "example-file-input",
      title = "File Input",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.ui._
      import io.laminext.syntax.validation.cats._
      import org.scalajs.dom.File

      val fileValidation: Validation[File, Seq[String], File] =
        V.file(Seq("must be <= 100MB"))(_.size <= 100 * 1024 * 1024) &
          V.file(Seq("must be a .png file"))(_.name.split('.').lastOption.map(_.toLowerCase).contains("png"))

      /* <focus> */
      val styling = FileInputConfig.classes {
        case FileInputElement.Status.Selecting => "btn-md-outline-blue"
        case FileInputElement.Status.Ready     => "btn-md-outline-green"
        case FileInputElement.Status.Invalid   => "btn-md-outline-red"
      }

      val fileInputButton = fileInput(
        validation = fileValidation,
        noFileError = Seq("no file selected"),
        styling = styling
      )

      val multiFileInputButton = multiFileInput(
        validation = fileValidation,
        noFileError = Seq("no file selected"),
        styling = styling
      )
      /* </focus> */

      div(
        cls := "p-4 space-y-4 bg-white",
        div(
          fileInputButton
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("fileInput.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- fileInputButton.validatedValue.eitherT.map(_.name).value.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("fileInput.validationError"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- fileInputButton.validationError.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          multiFileInputButton
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("fileInput.validatedValue:"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- multiFileInputButton.validatedValue.eitherT.map(_.map(_.name).mkString(", ")).value.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("fileInput.validationError"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- multiFileInputButton.validationError.map(_.toString)
            /* </focus> */
          )
        ),
      )
    })

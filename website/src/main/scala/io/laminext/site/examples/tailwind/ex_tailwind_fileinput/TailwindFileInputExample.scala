package io.laminext.site.examples.tailwind.ex_tailwind_fileinput

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindFileInputExample
    extends CodeExample(
      id = "example-file-input",
      title = "File Input",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import io.laminext.syntax.tailwind._
      import io.laminext.syntax.validation.cats._
      import io.laminext.validation.Validation
      import org.scalajs.dom.raw.File

      val fileValidation: Validation[File, Seq[String], File] =
        V.file(Seq("must be <= 100MB"))(_.size <= 100 * 1024 * 1024) &
          V.file(Seq("must be a .png file"))(_.name.split('.').lastOption.map(_.toLowerCase).contains("png"))

      val fileInput       = input(tpe := "file").validatedFile(Seq("no file"), fileValidation)
      val fileInputButton = button.btn.md.fill.build().fileInput(fileInput)

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
            child.text <-- fileInput.validatedValue.eitherT.map(_.name).value.map(_.toString)
            /* </focus> */
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("fileInput.validationError"),
          code(
            cls := "text-blue-700 font-medium",
            /* <focus> */
            child.text <-- fileInput.validationError.map(_.toString)
            /* </focus> */
          )
        )
      )
    })

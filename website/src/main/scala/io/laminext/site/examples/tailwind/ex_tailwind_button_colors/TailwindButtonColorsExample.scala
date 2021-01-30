package io.laminext.site.examples.tailwind.ex_tailwind_button_colors

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindButtonColorsExample
    extends CodeExample(
      id = "example-button-colors",
      title = "Button Colors",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      div(
        cls := "p-4 space-y-4 bg-white",
        div(
          cls := "space-x-4",
          button.btn.lg.fill.blue("fill.blue"),
          button.btn.lg.fill.blue(disabled := true, "fill.blue.disabled"),
          button.btn.lg.outline.blue("outline.blue"),
          button.btn.lg.text.blue("text.blue")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.green("fill.green"),
          button.btn.lg.fill.green(disabled := true, "fill.green.disabled"),
          button.btn.lg.outline.green("outline.green"),
          button.btn.lg.text.green("text.green")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.indigo("fill.indigo"),
          button.btn.lg.fill.indigo(disabled := true, "fill.indigo.disabled"),
          button.btn.lg.outline.indigo("outline.indigo"),
          button.btn.lg.text.indigo("text.indigo")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.red("fill.red"),
          button.btn.lg.fill.red(disabled := true, "fill.red.disabled"),
          button.btn.lg.outline.red("outline.red"),
          button.btn.lg.text.red("text.red")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.purple("fill.purple"),
          button.btn.lg.fill.purple(disabled := true, "fill.purple.disabled"),
          button.btn.lg.outline.purple("outline.purple"),
          button.btn.lg.text.purple("text.purple")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.yellow("fill.yellow"),
          button.btn.lg.fill.yellow(disabled := true, "fill.yellow.disabled"),
          button.btn.lg.outline.yellow("outline.yellow"),
          button.btn.lg.text.yellow("text.yellow")
        ),
        div(
          cls := "-mx-4 p-4 space-x-4 bg-cool-gray-900",
          button.btn.lg.fill.white("fill.white"),
          button.btn.lg.fill.white(disabled := true, "fill.white.disabled"),
          button.btn.lg.outline.white("outline.white"),
          button.btn.lg.text.white("text.white")
        ),
        div(
          cls := "space-x-4",
          button.btn.lg.fill.black("fill.black"),
          button.btn.lg.fill.black(disabled := true, "fill.black.disabled"),
          button.btn.lg.outline.black("outline.black"),
          button.btn.lg.text.black("text.black")
        ),
      )
    })

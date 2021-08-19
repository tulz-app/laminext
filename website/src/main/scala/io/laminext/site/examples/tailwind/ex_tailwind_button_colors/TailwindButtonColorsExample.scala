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
          cls := "space-x-4 flex",
          button.btn.lg.fill.blue(cls    := "flex-1 justify-center", "btn.lg.fill.blue"),
          button.btn.lg.fill.blue(cls    := "flex-1 justify-center", disabled := true, "btn.lg.fill.blue.disabled"),
          button.btn.lg.outline.blue(cls := "flex-1 justify-center", "btn.lg.outline.blue"),
          button.btn.lg.text.blue(cls    := "flex-1 justify-center", "btn.lg.text.blue")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.green(cls    := "flex-1 justify-center", "fill.green"),
          button.btn.lg.fill.green(cls    := "flex-1 justify-center", disabled := true, "fill.green.disabled"),
          button.btn.lg.outline.green(cls := "flex-1 justify-center", "outline.green"),
          button.btn.lg.text.green(cls    := "flex-1 justify-center", "text.green")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.indigo(cls    := "flex-1 justify-center", "fill.indigo"),
          button.btn.lg.fill.indigo(cls    := "flex-1 justify-center", disabled := true, "fill.indigo.disabled"),
          button.btn.lg.outline.indigo(cls := "flex-1 justify-center", "outline.indigo"),
          button.btn.lg.text.indigo(cls    := "flex-1 justify-center", "text.indigo")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.red(cls    := "flex-1 justify-center", "fill.red"),
          button.btn.lg.fill.red(cls    := "flex-1 justify-center", disabled := true, "fill.red.disabled"),
          button.btn.lg.outline.red(cls := "flex-1 justify-center", "outline.red"),
          button.btn.lg.text.red(cls    := "flex-1 justify-center", "text.red")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.purple(cls    := "flex-1 justify-center", "fill.purple"),
          button.btn.lg.fill.purple(cls    := "flex-1 justify-center", disabled := true, "fill.purple.disabled"),
          button.btn.lg.outline.purple(cls := "flex-1 justify-center", "outline.purple"),
          button.btn.lg.text.purple(cls    := "flex-1 justify-center", "text.purple")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.yellow(cls    := "flex-1 justify-center", "fill.yellow"),
          button.btn.lg.fill.yellow(cls    := "flex-1 justify-center", disabled := true, "fill.yellow.disabled"),
          button.btn.lg.outline.yellow(cls := "flex-1 justify-center", "outline.yellow"),
          button.btn.lg.text.yellow(cls    := "flex-1 justify-center", "text.yellow")
        ),
        div(
          cls := "-mx-4 p-4 flex space-x-4 bg-gray-900",
          button.btn.lg.fill.white(cls    := "flex-1 justify-center", "fill.white"),
          button.btn.lg.fill.white(cls    := "flex-1 justify-center", disabled := true, "fill.white.disabled"),
          button.btn.lg.outline.white(cls := "flex-1 justify-center", "outline.white"),
          button.btn.lg.text.white(cls    := "flex-1 justify-center", "text.white")
        ),
        div(
          cls := "space-x-4 flex",
          button.btn.lg.fill.black(cls    := "flex-1 justify-center", "fill.black"),
          button.btn.lg.fill.black(cls    := "flex-1 justify-center", disabled := true, "fill.black.disabled"),
          button.btn.lg.outline.black(cls := "flex-1 justify-center", "outline.black"),
          button.btn.lg.text.black(cls    := "flex-1 justify-center", "text.black")
        ),
      )
    })

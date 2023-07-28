package io.laminext.site.examples.resizeobserver

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object ResizeObserverExample
    extends CodeExample(
      id = "example-resize-observer",
      title = "Resize Observer",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._
      import org.scalajs.dom

      val textElement = textArea(
        cls  := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        rows := 3,
        resizeObserver --> { e =>
          dom.console.log(e)
        }
      )
      div(
        cls := "space-y-4",
        div(textElement),
        div(
          cls := "flex space-x-4",
          "Try resizing the text area."
        ),
        div(
          cls := "flex space-x-4",
          "Resize events are printed to the console."
        )
      )
    })

package io.laminext.site.examples.ui.ex_progress_bar

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object ProgressBarExample
    extends CodeExample(
      id = "example-progress-bar",
      title = "Progress Bar",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.ui._

      /* <focus> */
      val styling = ProgressBarElement.Styling(
        wrap = cls("w-full rounded bg-green-200"),
        progress = cls("bg-green-500 h-2 rounded")
      )
      val myProgress = EventStream.periodic(300).map(n => ((n * 3) % 100).toDouble).toSignal(0.0)
      /* </focus> */
      div(
        cls := "p-4 space-y-4 bg-white",
        /* <focus> */
        progressBar(
          progress = myProgress,
          styling = styling
        )
        /* </focus> */
      )
    })

package io.laminext.site.examples.tailwind.ex_tailwind_progress_bar

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object TailwindProgressBarExample
    extends CodeExample(
      id = "example-progress-bar",
      title = "Progress Bar",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.tailwind._

      div(
        cls := "p-4 space-y-4 bg-white",
        /* <focus> */
        TW.progressBar(EventStream.periodic(300).map(n => (n * 3 % 100).toDouble).toSignal(0.0))
        /* </focus> */
      )
    })

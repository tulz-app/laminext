package io.laminext.ui.syn

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.ui.theme.ProgressBarConfig
import io.laminext.ui.Theme
import org.scalajs.dom.html

trait ProgressBarCreate {

  def progressBar(
    progress: Signal[Double],
    styling: ProgressBarConfig = Theme.default.progressBar,
  ): ReactiveHtmlElement[html.Div] = {
    div(
      styling.wrap,
      div(
        styling.progress,
        width <-- progress.map(progress => s"${progress}%")
      )
    )
  }

}

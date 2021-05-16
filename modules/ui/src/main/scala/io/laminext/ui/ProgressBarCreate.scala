package io.laminext.ui

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

trait ProgressBarCreate {

  def progressBar(
    progress: Signal[Double],
    styling: ProgressBarElement.Styling
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

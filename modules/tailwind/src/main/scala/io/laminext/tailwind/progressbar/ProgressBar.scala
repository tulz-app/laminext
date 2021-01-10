package io.laminext.tailwind
package progressbar

import com.raquo.laminar.api.L._
import io.laminext.tailwind.syntax._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom.html

object ProgressBar {

  def apply(
    progress: Signal[Double],
    hidden: Signal[Boolean] = Val(false),
    thm: theme.ProgressBar = theme.Theme.current.progressBar
  ): ReactiveHtmlElement[html.Div] =
    div(
      cls := thm.wrap.classes,
      div(
        cls := thm.progress.classes,
        width <-- progress.map(progress => s"${progress}%")
      )
    ).hiddenIf(hidden)

}

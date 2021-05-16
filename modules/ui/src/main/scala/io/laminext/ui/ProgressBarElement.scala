package io.laminext.ui

import com.raquo.laminar.api.L._

object ProgressBarElement {

  final case class Styling(
    wrap: Modifier[HtmlElement],
    progress: Modifier[HtmlElement]
  )

}

package io.laminext.ui.theme

import com.raquo.laminar.api.L._

final case class ProgressBarConfig(
  wrap: Modifier[HtmlElement],
  progress: Modifier[HtmlElement]
)

object ProgressBarConfig {

  val empty: ProgressBarConfig = ProgressBarConfig(
    wrap = emptyMod,
    progress = emptyMod,
  )

}

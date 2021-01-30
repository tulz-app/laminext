package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

case class Transition(
  default: TransitionConfig,
  scale: TransitionConfig,
  opacity: TransitionConfig,
  opacityAndScale: TransitionConfig,
  resize: TransitionConfig,
)

object Transition {

  val empty: Transition = Transition(
    default = TransitionConfig.empty,
    scale = TransitionConfig.empty,
    opacity = TransitionConfig.empty,
    opacityAndScale = TransitionConfig.empty,
    resize = TransitionConfig.empty,
  )

}

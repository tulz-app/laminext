package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

case class Transition(
  default: TransitionConfig,
  scale: TransitionConfig,
  opacity: TransitionConfig,
  opacityAndScale: TransitionConfig,
  resize: TransitionConfig,
) {

  def customize(
    default: TransitionConfig => TransitionConfig = identity,
    scale: TransitionConfig => TransitionConfig = identity,
    opacity: TransitionConfig => TransitionConfig = identity,
    opacityAndScale: TransitionConfig => TransitionConfig = identity,
    resize: TransitionConfig => TransitionConfig = identity,
  ): Transition =
    Transition(
      default = default(this.default),
      scale = scale(this.scale),
      opacity = opacity(this.opacity),
      opacityAndScale = opacityAndScale(this.opacityAndScale),
      resize = resize(this.resize)
    )

}

object Transition {

  val empty: Transition = Transition(
    default = TransitionConfig.empty,
    scale = TransitionConfig.empty,
    opacity = TransitionConfig.empty,
    opacityAndScale = TransitionConfig.empty,
    resize = TransitionConfig.empty,
  )

}

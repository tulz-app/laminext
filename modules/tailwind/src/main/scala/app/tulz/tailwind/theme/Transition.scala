package app.tulz.tailwind.theme

import app.tulz.ui.transition.TransitionConfig

case class Transition(
  default: TransitionConfig,
  opacityAndScale: TransitionConfig,
  opacityAndScaleLong: TransitionConfig,
  opacity: TransitionConfig,
  opacityLong: TransitionConfig
)

object Transition {

  val opacityAndScale: TransitionConfig = TransitionConfig(
    hidden = "hidden",
    enter = "transform transition-all ease-out duration-100 motion-reduce:transition-none",
    enterFrom = "opacity-0 scale-75",
    enterTo = "opacity-100 scale-100",
    leave = "transform transition-all ease-in duration-150 motion-reduce:transition-none",
    leaveFrom = "opacity-100 scale-75",
    leaveTo = "opacity-0 scale-75"
  )

  val opacityAndScaleLong: TransitionConfig = opacityAndScale.copy(
    enter = "transform transition-all ease-out duration-300 motion-reduce:transition-none",
    leave = "transform transition-all ease-in duration-500 motion-reduce:transition-none"
  )

  val opacity: TransitionConfig = TransitionConfig(
    hidden = "hidden",
    enter = "transition-opacity ease-out duration-100 motion-reduce:transition-none",
    enterFrom = "opacity-0",
    enterTo = "opacity-100",
    leave = "transition-opacity ease-in duration-150 motion-reduce:transition-none",
    leaveFrom = "opacity-100",
    leaveTo = "opacity-0"
  )

  val opacityLong: TransitionConfig = opacity.copy(
    enter = "transition-opacity ease-out duration-500 motion-reduce:transition-none",
    leave = "transition-opacity ease-in duration-500 motion-reduce:transition-none"
  )

  val default: Transition = Transition(
    default = opacityAndScale,
    opacityAndScale = opacityAndScale,
    opacityAndScaleLong = opacityAndScaleLong,
    opacity = opacity,
    opacityLong = opacityLong
  )

}

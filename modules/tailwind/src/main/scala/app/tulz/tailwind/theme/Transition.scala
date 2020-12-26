package app.tulz.tailwind.theme

import app.tulz.laminar.ext._
import app.tulz.ui.transition.TransitionConfig

case class Transition(
  default: TransitionConfig
)

object Transition {

  val scale: TransitionConfig = TransitionConfig(
    inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enterDuration = "duration-100",
    enterTiming = "ease-out",
    enterFrom = "scale-75",
    enterTo = "scale-100",
    leaveDuration = "duration-150",
    leaveTiming = "ease-in",
    leaveFrom = "scale-100",
    leaveTo = "scale-75"
  )

  val opacity: TransitionConfig = TransitionConfig(
    inTransition = "transition-opacity motion-reduce:transition-none",
    enterDuration = "duration-100",
    enterTiming = "ease-out",
    enterFrom = "bg-opacity-0",
    enterTo = "bg-opacity-100",
    leaveDuration = "duration-150",
    leaveTiming = "ease-in",
    leaveFrom = "bg-opacity-100",
    leaveTo = "bg-opacity-0"
  )

  val opacityAndScale: TransitionConfig = TransitionConfig(
    inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enterDuration = "duration-100",
    enterTiming = "ease-out",
    enterFrom = "opacity-0 scale-75",
    enterTo = "opacity-100 scale-100",
    leaveDuration = "duration-150",
    leaveTiming = "ease-in",
    leaveFrom = "opacity-100 scale-100",
    leaveTo = "opacity-0 scale-75"
  )

  val opacityAndScaleLong: TransitionConfig = opacityAndScale.copy(
    enterTiming = classTokenize("duration-300"),
    leaveTiming = classTokenize("duration-500")
  )

  val opacityLong: TransitionConfig = opacity.copy(
    enterTiming = classTokenize("duration-300"),
    leaveTiming = classTokenize("duration-500")
  )

  val default: Transition = Transition(
    default = opacityAndScale
  )

}

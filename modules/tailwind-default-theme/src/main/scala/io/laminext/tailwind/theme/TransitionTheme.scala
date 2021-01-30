package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

object TransitionTheme {

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
    enterFrom = "opacity-0",
    enterTo = "opacity-100",
    leaveDuration = "duration-150",
    leaveTiming = "ease-in",
    leaveFrom = "opacity-100",
    leaveTo = "opacity-0"
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

  val resize: TransitionConfig = TransitionConfig(
    hidden = "",
    inTransition = "transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enterDuration = "duration-500",
    enterTiming = "linear",
    enterFrom = "",
    enterTo = "",
    leaveDuration = "duration-300",
    leaveTiming = "linear",
    leaveFrom = "",
    leaveTo = ""
  )

//  val opacityAndScaleLong: TransitionConfig = opacityAndScale.copy(
//    enterTiming = ClassTokenize("duration-300"),
//    leaveTiming = ClassTokenize("duration-500")
//  )
//
//  val opacityLong: TransitionConfig = opacity.copy(
//    enterTiming = ClassTokenize("duration-300"),
//    leaveTiming = ClassTokenize("duration-500")
//  )

}

package io.laminext.tailwind.theme

import io.laminext.ui.theme.TransitionConfig

object TailwindTransition {

  val scale: TransitionConfig = TransitionConfig(
    hidden = "hidden",
    inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enter = "duration-100 ease-out",
    enterFrom = "scale-75",
    enterTo = "scale-100",
    leave = "duration-150 ease-in",
    leaveFrom = "scale-100",
    leaveTo = "scale-75"
  )

  val opacity: TransitionConfig = TransitionConfig(
    hidden = "hidden",
    inTransition = "transition-opacity motion-reduce:transition-none",
    enter = "duration-100 ease-out",
    enterFrom = "opacity-0",
    enterTo = "opacity-100",
    leave = "duration-150 ease-in",
    leaveFrom = "opacity-100",
    leaveTo = "opacity-0"
  )

  val opacityAndScale: TransitionConfig = TransitionConfig(
    hidden = "hidden",
    inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enter = "duration-100 ease-out",
    enterFrom = "opacity-0 scale-75",
    enterTo = "opacity-100 scale-100",
    leave = "duration-150 ease-in",
    leaveFrom = "opacity-100 scale-100",
    leaveTo = "opacity-0 scale-75"
  )

  val resize: TransitionConfig = TransitionConfig(
    inTransition = "transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enter = "duration-500 linear",
    leave = "duration-300 linear",
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

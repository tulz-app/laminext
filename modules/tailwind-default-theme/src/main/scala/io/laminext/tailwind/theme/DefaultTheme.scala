package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

object DefaultTheme {

  val theme: Theme = Theme(
    animation = Animation(
      focusTransition = "transition ease-in-out duration-150"
    ),
    button = ButtonTheme.button,
    buttonGroup = BaseAndCustom(base = "relative z-0 inline-flex shadow-sm rounded-md"),
    card = Card(
      wrap = BaseAndCustom(base = "bg-white sm:shadow sm:rounded-lg"),
      header = BaseAndCustom(base = "px-4 py-3 sm:px-6 border-b border-gray-200"),
      body = BaseAndCustom(base = "p-4 md:px-6 md:rounded-lg"),
      footer = BaseAndCustom(base = "px-4 py-3 sm:px-6 border-t border-gray-200"),
      title = BaseAndCustom(base = "text-lg md:text-xl font-medium tracking-wide text-gray-700")
    ),
    transition = Transition(
      default = TransitionTheme.opacityAndScale,
      scale = TransitionTheme.scale,
      opacity = TransitionTheme.opacity,
      opacityAndScale = TransitionTheme.opacityAndScale,
      resize = TransitionTheme.resize
    ),
    modal = Modal(
      container = "fixed inset-0 z-50",
      overlayTransition = TransitionConfig(
        nonHidden = "hidden md:block md:absolute md:inset-0 bg-gray-900",
        showing = "bg-opacity-75",
        inTransition = "hidden md:absolute md:transition-opacity motion-reduce:transition-none",
        enterDuration = "duration-100",
        enterTiming = "ease-out",
        enterFrom = "bg-opacity-0",
        enterTo = "bg-opacity-75",
        leaveDuration = "duration-150",
        leaveTiming = "ease-in",
        leaveFrom = "bg-opacity-75",
        leaveTo = "bg-opacity-0"
      ),
      contentWrapTransition = TransitionTheme.scale.copy(
        nonHidden = ClassTokenize(
          "w-full h-screen pb-20 overflow-y-scroll bg-white",
          "md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl"
        ),
        showing = ClassTokenize("transform")
      ),
      contentWrapInner = "sm:my-0 sm:mx-auto sm:overlay-container"
    ),
    progressBar = ProgressBar.default,
    fileInput = FileInput(
      selecting = BaseAndCustom(
        base = Seq(
          ButtonTheme.button.color.blue.base,
          ButtonTheme.button.color.blue.outline,
          ButtonTheme.button.color.blue.single.fill
        ).mkString(" ")
      ),
      invalid = BaseAndCustom(
        base = Seq(
          ButtonTheme.button.color.red.base,
          ButtonTheme.button.color.red.outline,
          ButtonTheme.button.color.red.single.fill
        ).mkString(" ")
      ),
      ready = BaseAndCustom(
        base = Seq(
          ButtonTheme.button.color.green.base,
          ButtonTheme.button.color.green.fill,
          ButtonTheme.button.color.green.single.fill
        ).mkString(" ")
      )
    )
  )

}

package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

final case class Modal(
  container: String,
  overlayTransition: TransitionConfig,
  contentWrapTransition: TransitionConfig,
  contentWrapInner: String
)

object Modal {

  val default: Modal = Modal(
    container = "fixed inset-0 z-50",
    overlayTransition = Transition.opacity.copy(
      nonHidden = ClassTokenize("hidden md:block md:absolute md:inset-0 bg-gray-900"),
      showing = ClassTokenize("bg-opacity-75"),
      inTransition = ClassTokenize("hidden md:absolute md:transition-opacity motion-reduce:transition-none"),
      enterTo = ClassTokenize("bg-opacity-75"),
      leaveFrom = ClassTokenize("bg-opacity-75")
    ),
    contentWrapTransition = Transition.scale.copy(
      nonHidden = ClassTokenize(
        "w-full h-screen pb-20 overflow-y-scroll bg-white",
        "md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl"
      ),
      showing = ClassTokenize("transform")
    ),
    contentWrapInner = "sm:my-0 sm:mx-auto sm:overlay-container"
  )

}

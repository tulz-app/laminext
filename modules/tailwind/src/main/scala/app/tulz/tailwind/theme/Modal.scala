package app.tulz.tailwind.theme

import app.tulz.laminar.ext._
import app.tulz.ui.transition.TransitionConfig

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
      nonHidden = classTokenize("hidden md:block md:absolute md:inset-0 bg-gray-900"),
      showing = classTokenize("bg-opacity-75"),
      inTransition = classTokenize("hidden md:absolute md:transition-opacity motion-reduce:transition-none"),
      enterTo = classTokenize("bg-opacity-75"),
      leaveFrom = classTokenize("bg-opacity-75")
    ),
    contentWrapTransition = Transition.scale.copy(
      nonHidden = classTokenize(
        "w-full h-screen pb-20 overflow-y-scroll bg-white",
        "md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl"
      ),
      showing = classTokenize("transform")
    ),
    contentWrapInner = "sm:my-0 sm:mx-auto sm:overlay-container"
  )

}

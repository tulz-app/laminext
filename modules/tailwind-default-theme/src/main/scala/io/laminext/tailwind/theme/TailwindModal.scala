package io.laminext.tailwind.theme

import com.raquo.laminar.api.L._
import io.laminext.ui.theme.ModalConfig
import io.laminext.ui.theme.TransitionConfig

object TailwindModal {

  val styling: ModalConfig = ModalConfig(
    container = cls("fixed inset-0 z-50"),
    overlayTransition = TransitionConfig(
      nonHidden = "hidden md:block md:absolute md:inset-0 bg-gray-900",
      showing = "bg-opacity-75",
      inTransition = "hidden md:absolute md:transition-opacity motion-reduce:transition-none",
      enter = "duration-100 ease-out",
      enterFrom = "bg-opacity-0",
      enterTo = "bg-opacity-75",
      leave = "duration-150 ease-in",
      leaveFrom = "bg-opacity-75",
      leaveTo = "bg-opacity-0"
    ),
    contentWrapTransition = TailwindTransition.scale.copy(
      nonHidden =
        "w-full h-screen pb-20 overflow-y-scroll bg-white md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl",
      showing = "transform"
    ),
    contentWrapInner = cls("md:mx-auto")
  )

}

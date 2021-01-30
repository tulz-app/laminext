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

  val empty: Modal = Modal(
    container = "",
    overlayTransition = TransitionConfig.empty,
    contentWrapTransition = TransitionConfig.empty,
    contentWrapInner = ""
  )

}

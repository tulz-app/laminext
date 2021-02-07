package io.laminext.tailwind.theme

import io.laminext.ui.transition.TransitionConfig
import io.laminext.util.ClassTokenize

final case class Modal(
  container: BaseAndCustom,
  overlayTransition: TransitionConfig,
  contentWrapTransition: TransitionConfig,
  contentWrapInner: BaseAndCustom
) {

  def customize(
    container: BaseAndCustom => BaseAndCustom = identity,
    overlayTransition: TransitionConfig => TransitionConfig = identity,
    contentWrapTransition: TransitionConfig => TransitionConfig = identity,
    contentWrapInner: BaseAndCustom => BaseAndCustom = identity
  ): Modal = Modal(
    container = container(this.container),
    overlayTransition = overlayTransition(this.overlayTransition),
    contentWrapTransition = contentWrapTransition(this.contentWrapTransition),
    contentWrapInner = contentWrapInner(this.contentWrapInner)
  )

}

object Modal {

  val empty: Modal = Modal(
    container = BaseAndCustom.empty,
    overlayTransition = TransitionConfig.empty,
    contentWrapTransition = TransitionConfig.empty,
    contentWrapInner = BaseAndCustom.empty
  )

}

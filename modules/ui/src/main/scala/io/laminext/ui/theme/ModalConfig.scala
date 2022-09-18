package io.laminext.ui.theme

import com.raquo.laminar.api.L._

final case class ModalConfig(
  container: Mod[HtmlElement],
  overlayTransition: TransitionConfig,
  contentWrapTransition: TransitionConfig,
  contentWrapInner: Mod[HtmlElement]
) {

  def customize(
    container: Mod[HtmlElement] = this.container,
    overlayTransition: TransitionConfig => TransitionConfig = identity,
    contentWrapTransition: TransitionConfig => TransitionConfig = identity,
    contentWrapInner: Mod[HtmlElement] = this.contentWrapInner,
  ): ModalConfig = ModalConfig(
    container = container,
    overlayTransition = overlayTransition(this.overlayTransition),
    contentWrapTransition = contentWrapTransition(this.contentWrapTransition),
    contentWrapInner = contentWrapInner,
  )

}

object ModalConfig {

  val default: ModalConfig = ModalConfig(
    container = Seq(
      position.fixed,
      left   := "0",
      right  := "0",
      top    := "0",
      bottom := "0",
      zIndex := "50",
    ),
    overlayTransition = TransitionConfig.empty,
    contentWrapTransition = TransitionConfig.empty,
    contentWrapInner = emptyMod,
  )

  val noScrollClassName = "--modal-no-scroll"

}

package io.laminext.ui

import com.raquo.laminar.api.L._

final case class ModalContent(
  content: Element,
  closeObserver: Option[Observer[Unit]]
)

object ModalContent {

  def apply(
    content: Element,
    closeObserver: Option[Observer[Unit]]
  ): ModalContent = new ModalContent(content, closeObserver)

}

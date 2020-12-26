package app.tulz.tailwind.modal

import com.raquo.laminar.api.L._

final case class ModalContent(
  content: Element,
  closeObserver: Option[Observer[Unit]]
)

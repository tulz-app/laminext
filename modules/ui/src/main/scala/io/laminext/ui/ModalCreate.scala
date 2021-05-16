package io.laminext.ui

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.ui
import org.scalajs.dom

trait ModalCreate {
  self: TransitionCreate =>

  def modal(
    content: Signal[Option[ModalContent]],
    styling: Modal.Styling
  ): Element = {
    div(
      styling.container,
      content.bind { maybeContent =>
        if (maybeContent.isDefined) {
          dom.document.body.parentElement.classList.add(Modal.noScrollClassName)
        } else {
          dom.document.body.parentElement.classList.remove(Modal.noScrollClassName)
        }
      },
      div(
        addTransition(content.isDefined, styling.overlayTransition),
        thisEvents(onClick).withCurrentValueOf(content).foreach { case (_, maybeContent) =>
          maybeContent.flatMap(_.closeObserver).foreach(_.onNext((): Unit))
        }
      ),
      div(
        addTransition(content.isDefined, styling.contentWrapTransition),
        div(
          styling.contentWrapInner,
          child.maybe <-- content.optionMap(_.content)
        )
      ),
      display <-- content.isDefined.switch(
        "block",
        "none",
      )
    )
  }

}

package io.laminext.ui.syn

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.ui.theme.ModalConfig
import io.laminext.ui.Theme
import org.scalajs.dom

trait ModalCreate {
  self: TransitionCreate =>

  def initializeModal(): Unit = {
    val style = dom.document.createElement("style").asInstanceOf[dom.html.Style]
    style.`type` = "text/css"
    style.innerHTML = s""".${ModalConfig.noScrollClassName}{margin-right: ${calculateScrollbarWidth()}px;}
                         |html.${ModalConfig.noScrollClassName} body {overflow: hidden !important;}""".stripMargin
    val _     = dom.document.getElementsByTagName("head")(0).appendChild(style)
  }

  private def calculateScrollbarWidth(): Double = {
    val outer           = div(styleAttr("visibility: hidden; width: 100px; msOverflowStyle: scrollbar")).ref
    dom.document.body.appendChild(outer)
    val widthNoScroll   = outer.offsetWidth
    outer.style.overflow = "scroll"
    val inner           = div(width := "100%").ref
    outer.appendChild(inner)
    val widthWithScroll = inner.offsetWidth
    outer.parentNode.removeChild(outer)
    widthNoScroll - widthWithScroll
  }

  def modal(
    styling: ModalConfig = Theme.default.modal,
    closeObserver: Observer[Unit] = Observer.empty,
  )(
    content: Signal[Option[Element]],
  ): Element = {
    div(
      styling.container,
      content.bind { maybeContent =>
        if (maybeContent.isDefined) {
          dom.document.body.parentElement.classList.add(ModalConfig.noScrollClassName)
        } else {
          dom.document.body.parentElement.classList.remove(ModalConfig.noScrollClassName)
        }
      },
      div(
        addTransition(content.isDefined, styling.overlayTransition),
        onClick.mapToUnit --> closeObserver,
      ),
      div(
        addTransition(content.isDefined, styling.contentWrapTransition),
        div(
          styling.contentWrapInner,
          child.maybe <-- content,
        )
      ),
      display <-- content.isDefined.switch(
        "block",
        "none",
      )
    )
  }

}

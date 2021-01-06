package io.laminext.tailwind
package modal

import syntax._
import theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.syntax.all._
import org.scalajs.dom

object Modal {

  private val noScrollClassName = "--modal-no-scroll"

  def apply(
    content: Signal[Option[ModalContent]],
    config: theme.Modal = Theme.current.modal
  ): Element = {
    div(
      cls := config.container,
      content.bind { maybeContent =>
        if (maybeContent.isDefined) {
          dom.document.body.parentElement.classList.add(noScrollClassName)
        } else {
          dom.document.body.parentElement.classList.remove(noScrollClassName)
        }
      },
      div(
        TW.transition(content.isDefined, config.overlayTransition),
        thisEvents(onClick).withCurrentValueOf(content).forEach { case (_, maybeContent) =>
          maybeContent.flatMap(_.closeObserver).foreach(_.onNext((): Unit))
        }
      ),
      div(
        TW.transition(content.isDefined, config.contentWrapTransition),
        div(
          cls := config.contentWrapInner,
          child.maybe <-- content.optionMap(_.content)
        )
      )
    ).hiddenIf(content.isEmpty)
  }

  def initNoScrollClass(): Unit = {
    val style = dom.document.createElement("style").asInstanceOf[dom.html.Style]
    style.`type` = "text/css"
    style.innerHTML = s""".${noScrollClassName}{margin-right: ${calculateScrollbarWidth()}px;}
                         |html.${noScrollClassName} body {overflow: hidden !important;}""".stripMargin
    val _ = dom.document.getElementsByTagName("head")(0).appendChild(style)
  }

  private def calculateScrollbarWidth(): Double = {
    val outer = div(styleAttr("visibility: hidden; width: 100px; msOverflowStyle: scrollbar")).ref
    dom.document.body.appendChild(outer)
    val widthNoScroll = outer.offsetWidth
    outer.style.overflow = "scroll"
    val inner = div(width := "100%").ref
    outer.appendChild(inner)
    val widthWithScroll = inner.offsetWidth
    outer.parentNode.removeChild(outer)
    widthNoScroll - widthWithScroll
  }

}

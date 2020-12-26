package app.tulz.tailwind
package modal

import theme.Theme
import com.raquo.laminar.api.L.{transition => _, _}
import app.tulz.laminar.ext._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom
import org.scalajs.dom.html

object Modal {

  private val noScrollClassName = "--modal-no-scroll"

  def apply(
    content: Signal[Option[ModalContent]],
    config: theme.Modal = Theme.current.modal
  ): ReactiveHtmlElement[html.Div] = {
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
        transition(content.isDefined, config.overlayTransition),
        onClick.stream.withCurrentValueOf(content).bind { case (_, maybeContent) =>
          maybeContent.flatMap(_.closeObserver).foreach(_.onNext((): Unit))
        }
      ),
      div(
        transition(content.isDefined, config.contentWrapTransition),
        div(
          //            onClick --> (_.stopPropagation()),
          cls := config.contentWrapInner,
          child.maybe <-- content.mapOption { overlayContent =>
            overlayContent.content
          }
        )
      )
    ).hiddenIf(content.isEmpty)
  }

  def initNoScrollClass(): Unit = {
    val style = dom.document.createElement("style").asInstanceOf[dom.html.Style]
    style.`type` = "text/css"
    style.innerHTML = s""".${noScrollClassName}{
                         |margin-right: ${calculateScrollbarWidth()}px;
                         |}
                         |html.${noScrollClassName} body {
                         |overflow: hidden !important;
                         |}
                         |""".stripMargin
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

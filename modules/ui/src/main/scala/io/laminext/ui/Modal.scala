package io.laminext.ui

import com.raquo.laminar.api.L._
import org.scalajs.dom

object Modal {

  val noScrollClassName = "--modal-no-scroll"

  def initialize(): Unit = {
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

  final case class Styling(
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
    ): Styling = Styling(
      container = container,
      overlayTransition = overlayTransition(this.overlayTransition),
      contentWrapTransition = contentWrapTransition(this.contentWrapTransition),
      contentWrapInner = contentWrapInner,
    )
  }

}

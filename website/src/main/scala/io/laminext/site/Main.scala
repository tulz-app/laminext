package io.laminext.site

import com.raquo.laminar.api.L._
import frontroute.BrowserNavigation
import frontroute.LinkHandler
import io.laminext.highlight.Highlight
import io.laminext.highlight.HighlightCss
import io.laminext.highlight.HighlightJavaScript
import io.laminext.highlight.HighlightJson
import io.laminext.highlight.HighlightScala
import io.laminext.highlight.HighlightXml
import io.laminext.site.components.CodeExampleDisplay
import io.laminext.tailwind.modal.Modal
import io.laminext.tailwind.theme.DefaultTheme
import io.laminext.tailwind.theme.Theme
import org.scalajs.dom

object Main {

  def main(args: Array[String]): Unit = {
    val _ = documentEvents(_.onDomContentLoaded).foreach { _ =>
      Theme.setTheme(DefaultTheme.theme)
      Modal.initialize()
      val wiring = Wiring()
      removeNoJsClass(wiring.ssrContext)
      insertJsClass(wiring.ssrContext)
      Highlight.registerLanguage("scala", HighlightScala)
      Highlight.registerLanguage("javascript", HighlightJavaScript)
      Highlight.registerLanguage("css", HighlightCss)
      Highlight.registerLanguage("json", HighlightJson)
      Highlight.registerLanguage("html", HighlightXml)
      if (dom.window.location.pathname.startsWith(Site.thisVersionHref("/example-frame/"))) {
        renderExample()
      } else {
        wiring.routes.start()
      }
    }(unsafeWindowOwner)
  }

  private def renderExample(): Unit = {
    val id           = dom.window.location.pathname.drop(Site.thisVersionHref("/example-frame/").length).takeWhile(_ != '/')
    val appContainer = dom.document.querySelector("#app")
    val content      = Site.allExamples.find(_.id == id).map(ex => CodeExampleDisplay.frame(ex)).getOrElse(div(s"EXAMPLE NOT FOUND: ${id}"))
    val _            = com.raquo.laminar.api.L.render(appContainer, content.amend(LinkHandler.bind))
    BrowserNavigation.pushState(url = "/")
  }

  private def insertJsClass(ssrContext: SsrContext): Unit = {
    if (!ssrContext.ssr) {
      val style = dom.document.createElement("style").asInstanceOf[dom.html.Style]
      style.`type` = "text/css"
      style.innerHTML = s""".hidden-if-js{display: none;}""".stripMargin
      val _     = dom.document.getElementsByTagName("head")(0).appendChild(style)
    }
  }

  private def removeNoJsClass(ssrContext: SsrContext): Unit = {
    if (!ssrContext.ssr) {
      Option(dom.document.head.querySelector("style#no-js")).foreach(dom.document.head.removeChild(_))
    }
  }

}

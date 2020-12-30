package io.laminext.site

import io.frontroute.BrowserNavigation
import org.scalajs.dom
import org.scalajs.dom.raw._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

object LinkHandler {
  @js.native
  @JSGlobal("window")
  private object GlobalWindow extends js.Object {
    var routeTo: js.Function1[String, Unit] = js.native
  }

  def install(): Unit = {
    GlobalWindow.routeTo = path => BrowserNavigation.pushState(null, null, path)
    dom.document.body.onclick = e => {
      findParent("a", e.target.asInstanceOf[Node]).fold(true) { element =>
        val anchor = element.asInstanceOf[HTMLAnchorElement]
        val rel    = anchor.rel
        if (js.isUndefined(rel) || rel == null || rel == "") {
          BrowserNavigation.pushState(url = anchor.href)
          false
        } else {
          rel match {
            case "external" =>
              e.preventDefault()
              dom.window.open(anchor.href)
              false

            case _ =>
              true
          }
        }
      }
    }
  }

  @scala.annotation.tailrec
  private def findParent(tagName: String, element: Node): Option[Node] = {
    if (js.isUndefined(element) || element == null) {
      Option.empty
    } else {
      val tagMatched =
        element.nodeName
          .asInstanceOf[js.UndefOr[String]]
          .orElse(
            element.asInstanceOf[Element].tagName.asInstanceOf[js.UndefOr[String]]
          )
          .map(_.toLowerCase())
          .contains(tagName.toLowerCase())
      if (tagMatched) {
        Some(element)
      } else {
        findParent(tagName, element.parentNode)
      }
    }
  }

}

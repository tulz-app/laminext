package io.laminext.site.layout

import io.laminext.site.Page
import io.laminext.site.Site
import io.laminext.site.SiteModule
import io.laminext.site.Styles
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._
import io.laminext.ui._
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageWrap {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]],
    menuObserver: Observer[Option[ModalContent]]
  ): ReactiveHtmlElement.Base = {
    val titleElement   = org.scalajs.dom.document.head.querySelector("title")
    val $pageAndResult = $page.optionMap(p => (p, p.render()))
    div(
      linkTag(
        rel := "stylesheet",
        href <-- Styles.highlightStyle.signal.map(s => s"/stylesheets/highlightjs/${s}.css")
      ),
      div(
        cls := "h-screen flex flex-col",
        PageHeader($module, $page, menuObserver),
        noScript(
          div(
            cls := "max-w-5xl border-l-4 border-red-400 bg-red-50 text-red-900 mx-auto p-4 font-condensed",
            "Your browser does not support JavaScript: some features of this site may not work properly."
          )
        ),
        div(
          cls := "flex-1 flex overflow-hidden",
          PageNavigation($module, $page).hiddenIf($module.optionContains(Site.indexModule)),
          div(
            cls := "flex-1 bg-gray-200 overflow-auto md:p-4",
            div(
              cls := "lg:container lg:mx-auto lg:max-w-4xl lg:p-8 p-4 bg-white min-h-full",
              child <-- $pageAndResult.map {
                case Some((_, Right((el, _))))     => el
                case Some((_, Left((_, message)))) => div(message)
                case None                          => div("loading...")
              }
            )
          )
        ),
        PageFooter()
      ),
      $pageAndResult.bind {
        case Some((_, Right((_, theTitle)))) =>
          titleElement.textContent = s"$theTitle - laminext"
          org.scalajs.dom.document.title = s"$theTitle - laminext"
          if (theTitle.nonEmpty) {
            if (theTitle == "Not Found") {
              titleElement.setAttribute("data-status", "404")
            } else {
              titleElement.setAttribute("data-status", "200")
            }
          }
        case Some((_, Left((code, _))))      =>
          titleElement.textContent = s"Error"
          org.scalajs.dom.document.title = s"Error"
          titleElement.setAttribute("data-status", code.toString)
        case None                            =>
          titleElement.textContent = s"Loading..."
          org.scalajs.dom.document.title = s"Loading..."
          titleElement.removeAttribute("data-status")
      }
    )
  }

}

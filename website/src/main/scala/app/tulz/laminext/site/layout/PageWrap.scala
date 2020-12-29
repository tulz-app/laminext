package app.tulz.laminext.site.layout

import app.tulz.laminext.site.Page
import app.tulz.laminext.site.SiteModule
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageWrap {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base = {
    val titleElement   = org.scalajs.dom.document.head.querySelector("title")
    val $pageAndResult = $page.optionMap(p => (p, p.render()))
    div(
      cls := "h-screen flex flex-col",
      PageHeader($module, $page),
      noScript(
        div(
          cls := "max-w-5xl border-l-4 border-red-400 bg-red-50 text-red-900 mx-auto p-4 font-condensed",
          "Your browser does not support JavaScript: some features of this site may not work properly."
        )
      ),
      div(
        cls := "flex-1 flex overflow-hidden",
        PageNavigation($module, $page),
        div(
          cls := "flex-1 bg-cool-gray-200 overflow-auto p-4",
          div(
            cls := "container mx-auto p-4 bg-white",
            child <-- $pageAndResult.map {
              case Some((_, Right((el, _))))     => el
              case Some((_, Left((_, message)))) => div(message)
              case None                          => div("loading...")
            }
          )
        )
      ),
      PageFooter(),
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
        case Some((_, Left((code, _)))) =>
          titleElement.textContent = s"Error"
          org.scalajs.dom.document.title = s"Error"
          titleElement.setAttribute("data-status", code.toString)
        case None =>
          titleElement.textContent = s"Loading..."
          org.scalajs.dom.document.title = s"Loading..."
          titleElement.removeAttribute("data-status")
      }
    )
  }

}

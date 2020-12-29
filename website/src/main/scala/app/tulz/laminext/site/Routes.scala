package app.tulz.laminext.site

import app.tulz.laminext.site.layout.PageWrap
import app.tulz.laminext.site.pages.IndexPage
import app.tulz.laminext.site.pages.PageResult
import com.raquo.airstream.signal.Var
import com.raquo.laminar.api.L.div
import com.raquo.laminar.api.L.unsafeWindowOwner
import io.frontroute._
import io.frontroute.directives._
import org.scalajs.dom

class Routes(
  routeLocationProvider: RouteLocationProvider
) {

  private def oneOf(options: Seq[String]): PathMatcher1[String] =
    segment.collect("oneOf") {
      case s if options.contains(s) => s
    }

  private val renders = Var[Option[Page]](None)

  private def render(page: Page): Route =
    complete {
//        appEvents.onNext(AppEvent.hideOverlay())
      renders.writer.onNext(Some(page))
      dom.window.scrollTo(0, 0)
    }
  private def notFound: Route =
    complete {
//        appEvents.onNext(AppEvent.hideOverlay())
      renders.writer.onNext(Some(Page("", "Not Found", () => Left((404, "Not Found")))))
      dom.window.scrollTo(0, 0)
    }

  private val route =
    concat(
      extractUnmatchedPath { unmatched =>
        provide(Site.findPage(unmatched)) {
          case Some(page) =>
            render(page)
          case None => reject
        }
      },
      notFound
    )

  def start(): Unit = {
    val appContainer = dom.document.querySelector("#app")
//    val modalContainer = dom.document.querySelector("#overlay")
    val appContent = PageWrap($page = renders.signal)

    appContainer.innerHTML = ""
    com.raquo.laminar.api.L.render(appContainer, appContent)
//    com.raquo.laminar.api.L.render(modalContainer, Modal($modalContent))

    runRoute(route, routeLocationProvider)(unsafeWindowOwner)

    BrowserNavigation.emitPopStateEvent()
  }

}

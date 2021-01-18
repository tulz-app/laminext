package io.laminext.site

import io.laminext.site.layout.PageWrap
import com.raquo.airstream.state.Var
import com.raquo.laminar.api.L.unsafeWindowOwner
import io.laminext.syntax.all._
import io.frontroute._
import io.frontroute.directives._
import org.scalajs.dom

class Routes(locationProvider: LocationProvider) {

  private val $module = Var[Option[SiteModule]](None)
  private val $page   = Var[Option[Page]](None)

  private def render(module: SiteModule, page: Page): Route =
    complete {
//        appEvents.onNext(AppEvent.hideOverlay())
      $module.writer.onNext(Some(module))
      $page.writer.onNext(Some(page))
      dom.window.scrollTo(0, 0)
    }
  private def notFound: Route =
    complete {
//      $page.writer.onNext(Some(Page("", "Not Found", () => Left((404, "Not Found")))))
      dom.window.scrollTo(0, 0)
    }

  private def modulePrefix =
    pathPrefix(segment).flatMap { moduleName =>
      provide(Site.findModule(moduleName)).collect { case Some(module) =>
        Tuple1(module)
      }
    }

  private def modulePagePrefix(module: SiteModule) =
    pathPrefix(segment).flatMap { pageName =>
      provide(module.findPage(pageName)).collect { case Some(page) =>
        Tuple1(page)
      }
    }

  private val route =
    concat(
      pathEnd {
        render(Site.indexModule, Site.indexModule.index)
      },
      modulePrefix { module =>
        concat(
          pathEnd {
            render(module, module.index)
          },
          modulePagePrefix(module) { page =>
            render(module, page)
          }
        )
      },
      notFound
    )

  def start(): Unit = {
    val appContainer = dom.document.querySelector("#app")
//    val modalContainer = dom.document.querySelector("#overlay")
    val appContent = PageWrap($module.signal, $page.signal)

    appContainer.innerHTML = ""
    com.raquo.laminar.api.L.render(appContainer, appContent)
//    com.raquo.laminar.api.L.render(modalContainer, Modal($modalContent))

    runRoute(route, locationProvider)(unsafeWindowOwner)

    BrowserNavigation.emitPopStateEvent()
  }

}

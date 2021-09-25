package io.laminext.site

import com.raquo.laminar.api.L._
import io.laminext.site.layout.PageWrap
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._
import io.frontroute._
import io.laminext.tailwind.modal.ModalContent
import io.laminext.tailwind.theme.Modal
import io.laminext.tailwind.theme.Theme
import org.scalajs.dom

class Routes(locationProvider: LocationProvider) {

  private val mobileMenuContent = Var[Option[ModalContent]](None)

  private def notFound: Route                      =
    complete {
//      $page.writer.onNext(Some(Page("", "Not Found", () => Left((404, "Not Found")))))
      dom.window.scrollTo(0, 0)
    }

  private def modulePrefix                         =
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

  private val reset: () => Unit                    = () => {
    mobileMenuContent.writer.onNext(None)
    dom.window.scrollTo(0, 0)
  }

  private val (routeResult, route)                 = makeRouteWithCallback[(SiteModule, Page)](reset) { render =>
    concat(
      pathEnd {
        render(Site.indexModule -> Site.indexModule.index)
      },
      modulePrefix { module =>
        concat(
          pathEnd {
            render(module -> module.index)
          },
          modulePagePrefix(module) { page =>
            render(module -> page)
          }
        )
      },
      notFound
    )
  }

  private val $module                              = routeResult.optionMap(_._1)
  private val $page                                = routeResult.optionMap(_._2)

  private val mobileMenuModal: Modal = Theme.current.modal.customize(
    contentWrapTransition = _.customize(
      nonHidden = _ :+ "bg-gray-900"
    )
  )

  def start(): Unit = {
    val appContainer  = dom.document.querySelector("#app")
    val menuContainer = dom.document.querySelector("#menu-modal")
    val appContent    = PageWrap($module.signal, $page.signal, mobileMenuContent.writer)

    appContainer.innerHTML = ""
    com.raquo.laminar.api.L.render(appContainer, appContent)
    com.raquo.laminar.api.L.render(menuContainer, TW.modal(mobileMenuContent.signal, mobileMenuModal))

    runRoute(route, locationProvider)(unsafeWindowOwner)

    BrowserNavigation.emitPopStateEvent()
  }

}

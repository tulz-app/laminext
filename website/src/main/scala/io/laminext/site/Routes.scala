package io.laminext.site

import com.raquo.laminar.api.L._
import io.laminext.site.layout.PageWrap
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._
import frontroute._
import io.laminext.tailwind.modal.ModalContent
import io.laminext.tailwind.theme.Modal
import io.laminext.tailwind.theme.Theme
import org.scalajs.dom

class Routes {

  private val mobileMenuContent = Var[Option[ModalContent]](None)

  private def modulePrefix: Directive[SiteModule] =
    pathPrefix(segment).flatMap { moduleName =>
      provide(Site.findModule(moduleName)).collect { case Some(module) =>
        module
      }
    }

  private def moduleAndPagePrefix: Directive[(SiteModule, Page)] =
    modulePrefix.flatMap { module =>
      pathPrefix(segment).flatMap { pageName =>
        provide(module.findPage(pageName)).collect { case Some(page) =>
          (module, page)
        }
      }
    }

  private val versionSegment = {
    regex("\\d+\\.\\d+\\.\\S+".r).map(_.source)
  }

  private val versionPrefix =
    pathPrefix("v" / versionSegment)

  private val thisVersionPrefix =
    versionPrefix.filter(_.toString.startsWith(Site.laminextSiteVersion)).mapTo(())

  private val anyVersionPrefix =
    versionPrefix.mapTo(())

  private val mobileMenuModal: Modal = Theme.current.modal.customize(
    contentWrapTransition = _.customize(
      nonHidden = _ :+ "bg-gray-900"
    )
  )

  def start(): Unit = {
    val appContainer  = dom.document.querySelector("#app-container")
    val menuContainer = dom.document.querySelector("#menu-modal")

    appContainer.innerHTML = ""
    val _ = com.raquo.laminar.api.L.render(
      appContainer,
      routes(
        div(
          cls := "contents",
          LinkHandler.bind,
          thisVersionPrefix(
            firstMatch(
              (
                pathEnd.mapTo(Some((Site.indexModule, Site.indexModule.index))) |
                  (modulePrefix & pathEnd).map(m => Some((m, m.index))) |
                  moduleAndPagePrefix.map(moduleAndPage => Some(moduleAndPage))
              ).signal { moduleAndPage =>
                PageWrap(moduleAndPage, mobileMenuContent.writer)
              },
              div("Not Found")
            )
          ),
          (noneMatched & anyVersionPrefix) {
            div("Not Found (wrong version)")
          },
          noneMatched {
            div("Not Found")
          }
        )
      )
    )
    val _ = com.raquo.laminar.api.L.render(menuContainer, TW.modal(mobileMenuContent.signal, mobileMenuModal))

  }

}

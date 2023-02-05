package io.laminext.site

import com.raquo.laminar.api.L._
import io.laminext.site.layout.PageWrap
import io.frontroute._
import org.scalajs.dom

class Routes {

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
    versionPrefix.filter(_.toString.startsWith(Site.laminextVersion)).mapTo(())

  private val anyVersionPrefix =
    versionPrefix.mapTo(())

  def start(): Unit = {
    val appContainer = dom.document.querySelector("#app")

    appContainer.innerHTML = ""
    val _ = com.raquo.laminar.api.L.render(
      appContainer,
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
              PageWrap(moduleAndPage)
            },
            div("Not Found")
          )
        ),
        (noneMatched & anyVersionPrefix) {
          runEffect {
            dom.window.location.reload()
          }
        },
        noneMatched {
          div("Not Found")
        }
      )
    )
  }

}

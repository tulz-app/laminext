package app.tulz.laminext.site.layout

import com.raquo.laminar.api.L._
import app.tulz.laminext.site.Page
import app.tulz.laminext.site.Site
import app.tulz.laminext.site.SiteModule
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageHeader {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base =
    div(
      cls := "bg-cool-gray-900 text-white flex p-4 items-center space-x-8",
      div(
        cls := "flex-shrink-0",
        "app.tulz.laminext"
      ),
      nav(
        cls := "flex-1 flex space-x-2 justify-start",
        Site.modules.map { module =>
          a(
            cls <-- $module.map { currentModule =>
              Seq(
                "px-2 py-2 flex text-sm font-medium rounded-md"               -> true,
                "text-cool-gray-300 hover:bg-cool-gray-700 hover:text-white " -> !currentModule.exists(_.path == module.path),
                "bg-cool-gray-700 text-white"                                 -> currentModule.exists(_.path == module.path)
              )
            },
            href := s"/${module.path}",
            module.index.title
          )
        }
      )
    )

}

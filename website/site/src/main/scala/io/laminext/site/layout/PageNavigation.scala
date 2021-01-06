package io.laminext.site.layout

import io.laminext.site.Page
import io.laminext.site.Site
import io.laminext.site.SiteModule
import io.laminext.syntax.all._
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageNavigation {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base =
    nav(
      cls := "w-80 p-4 overflow-auto bg-cool-gray-800 text-white",
      child.maybe <-- $module.optionMap { module =>
        div(
          cls := "space-y-4",
          div(
            cls := "-mx-2 px-2 py-2 hover:bg-cool-gray-700 group",
            a(
              cls := "border-l-4 -ml-2 pl-2 border-transparent flex text-xl font-display font-bold",
              $page.map(_.exists(_.path.isEmpty)).classSwitch(
                whenTrue = "text-cool-gray-100 border-cool-gray-400",
                whenFalse = "text-cool-gray-400 group-hover:text-cool-gray-100"
              ),
              href := (if (module.path == Site.indexModule.path) "/" else s"/${module.path}"),
              module.index.title
            )
          ),
          module.navigation.map { case (title, pages) =>
            div(
//              cls := "space-y-1",
              div(
                cls := "pl-1 text-xl font-display font-semibold text-cool-gray-400 tracking-wide",
                title
              ),
              pages.map { page =>
                div(
                  cls := "-mx-4 pl-6 pr-2 py-2 hover:bg-cool-gray-700 group",
                  a(
                    cls := "border-l-4 -ml-4 pl-4 border-transparent flex font-display font-medium tracking-wide",
                    $page.map(_.exists(_.path == page.path)).classSwitch(
                      whenTrue = "text-white border-cool-gray-100",
                      whenFalse = "text-cool-gray-300 group-hover:text-white"
                    ),
                    href := s"/${module.path}/${page.path}",
                    page.title
                  )
                )
              }
            )
          }
        )
      }
    )

}

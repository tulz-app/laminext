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
      cls := "w-80 py-4 overflow-auto bg-cool-gray-800 text-white",
      child.maybe <-- $module.optionMap { module =>
        div(
          cls := "space-y-4",
          navigationItem($page, module.index)(
            a(
              cls := "ml-2 flex text-xl font-display font-bold",
              href := s"/${module.path}",
              module.index.title
            )
          ),
          module.navigation.map { case (title, pages) =>
            div(
              when(title.nonEmpty) {
                div(
                  cls := "ml-4 text-xl font-display font-semibold text-cool-gray-400 tracking-wide",
                  title
                )
              },
              pages.map { page =>
                navigationItem($page, page)(
                  a(
                    cls := "ml-6 flex font-display font-medium tracking-wide",
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

  private def navigationItem(
    $currentPage: Signal[Option[Page]],
    page: Page
  )(mods: Modifier[Element]*) =
    div(
      cls := "px-2 py-1",
      $currentPage.optionExists(_.path == page.path).classSwitch(
        whenTrue = "text-white bg-cool-gray-700",
        whenFalse = "text-cool-gray-200 hover:text-white hover:bg-cool-gray-700"
      ),
      mods
    )

}

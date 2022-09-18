package io.laminext.site.layout

import io.laminext.site.Page
import io.laminext.site.Site
import io.laminext.site.SiteModule
import io.laminext.syntax.core._
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageNavigation {

  def apply(
    $page: Signal[Option[(SiteModule, Page)]],
    mobile: Boolean = false
  ): ReactiveHtmlElement.Base =
    nav(
      cls := "py-4 overflow-auto bg-gray-800 text-white",
      cls := (if (mobile) "" else "w-80 hidden lg:block"),
      child.maybe <-- $page.optionMap { case (module, _) =>
        div(
          cls := "space-y-4",
          when(module.index.title.nonEmpty) {
            navigationItem($page, module.index)(
              a(
                cls  := "ml-2 flex text-xl font-display font-bold",
                href := Site.thisVersionHref(s"/${module.path}"),
                module.index.title
              )
            )
          },
          module.navigation.map { case (title, pages) =>
            div(
              when(title.nonEmpty) {
                div(
                  cls := "ml-4 text-xl font-display font-semibold text-gray-400 tracking-wide",
                  title
                )
              },
              pages.map { page =>
                navigationItem($page, page)(
                  a(
                    cls  := "ml-6 flex font-display font-medium tracking-wide",
                    href := Site.thisVersionHref(s"/${module.path}/${page.link}"),
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
    $currentPage: Signal[Option[(SiteModule, Page)]],
    page: Page
  )(mods: Modifier[Element]*) =
    div(
      cls := "px-2 py-1",
      $currentPage.optionExists(_._2.path == page.path).classSwitch(
        whenTrue = "text-white bg-gray-700",
        whenFalse = "text-gray-200 hover:text-white hover:bg-gray-700"
      ),
      mods
    )

}

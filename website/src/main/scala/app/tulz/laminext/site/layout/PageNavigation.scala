package app.tulz.laminext.site.layout

import app.tulz.laminext.site.Page
import app.tulz.laminext.site.Site
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageNavigation {

  def apply(
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base =
    nav(
      cls := "w-96 p-4 overflow-auto bg-cool-gray-800 text-white space-y-4",
      Site.navigationSections.map { case (title, pages) =>
        div(
          cls := "space-y-1",
          span(
            cls := "text-xl font-medium text-cool-gray-200",
            title
          ),
          div(
            cls := "ml-2",
            pages.map { page =>
              a(
                cls <-- $page.map { currentPage =>
                  Seq(
                    "px-2 py-2 flex text-sm font-medium rounded-md"               -> true,
                    "text-cool-gray-300 hover:bg-cool-gray-700 hover:text-white " -> !currentPage.exists(_.path == page.path),
                    "bg-cool-gray-900 text-white"                                 -> currentPage.exists(_.path == page.path)
                  )
                },
                href := page.path,
                page.title
              )
            }
          )
        )
      }
    )

}

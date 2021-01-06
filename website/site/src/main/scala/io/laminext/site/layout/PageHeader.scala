package io.laminext.site

package layout

import com.raquo.laminar.api.L._
import io.laminext.tailwind.syntax._
import io.laminext.syntax.all._
import io.laminext.site.icons.Icons
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.tailwind.TW

object PageHeader {

  def apply(
    $module: Signal[Option[SiteModule]],
    $page: Signal[Option[Page]]
  ): ReactiveHtmlElement.Base = {
    val styleDropDownOpen = Var(false)
    val styleSearch       = Var("")
    val searchInput = input(
      cls := "appearance-none block w-full px-3 py-2 rounded-md text-cool-gray-900 border border-gray-300 placeholder-gray-400 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500 transition duration-150 ease-in-out",
      placeholder := "search..."
    )
    searchInput.amend(
      searchInput.value --> styleSearch.writer
    )

    div(
      cls := "bg-cool-gray-900 text-white flex py-4 px-8 items-center space-x-8 ",
      nav(
        cls := "flex-1 flex space-x-4 justify-start",
        Site.modules.map { module =>
          a(
            cls := "border-b-2 px-2 border-transparent flex text-lg font-display font-medium tracking-wide",
            $module.map(_.exists(_.path == module.path)).classSwitch(
              "border-cool-gray-300 text-white",
              "text-cool-gray-300 hover:border-cool-gray-300 hover:text-white "
            ),
            href := s"/${module.path}",
            module.index.title
          )
        }
      ),
      div(
        cls := "relative inline-block text-left",
        div(
          button.btn.sm.text.white(
            `type` := "button",
            aria.hasPopup := true,
            aria.expanded <-- styleDropDownOpen.signal,
            onClick --> { _ => styleDropDownOpen.toggle() },
            "code theme",
            Icons
              .chevronDown(
                svg.cls := "-mr-1 ml-2 h-4 fill-current text-cool-gray-300"
              ).hiddenIf(styleDropDownOpen.signal),
            Icons
              .chevronUp(
                svg.cls := "-mr-1 ml-2 h-4 fill-current text-cool-gray-300"
              ).visibleIf(styleDropDownOpen.signal)
          )
        ),
        div(
          TW.transition(styleDropDownOpen.signal),
          cls := "origin-top-right absolute max-h-128 overflow-auto right-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-20 p-2",
          div(
            cls := "py-1",
            role := "menu",
            aria.orientation := "vertical",
            aria.labelledBy := "options-menu",
            div(
              cls := "mb-2",
              searchInput
            ),
            Styles.styles.map { styleName =>
              button(
                cls := "block flex items-center space-x-2 w-full px-4 py-2 text-left text-cool-gray-700 hover:bg-cool-gray-200 hover:text-cool-gray-900",
                onClick.mapTo(styleName) --> Styles.highlightStyle.setObserver,
                role := "menuitem",
                span(
                  cls := "flex-1",
                  styleName,
                ),
                span(
                  Icons
                    .check(svg.cls := "h-6 text-green-500 fill-current")
                    .visibleIf(Styles.highlightStyle.signal.valueIs(styleName)),
                )
              ).hiddenIf(
                styleSearch.signal.map(search => !styleName.contains(search))
              )
            }
          )
        )
      ),
      div(
        a(
          href := "https://github.com/tulz-app/laminext",
          Icons.github(svg.cls := "h-8")
        )
      )
    )
  }

}

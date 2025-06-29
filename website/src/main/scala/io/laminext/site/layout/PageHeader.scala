package io.laminext.site

package layout

import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._
import io.laminext.syntax.core._
import io.laminext.site.icons.Icons
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageHeader {

  def apply(
    $page: Signal[Option[(SiteModule, Page)]],
    menuObserver: Observer[Option[ModalContent]]
  ): ReactiveHtmlElement.Base = {
    val styleDropDownOpen = Var(false)
    val styleSearch       = Var("")
    val searchInput       = input(
      cls         := "appearance-none block w-full px-3 py-2 rounded-md text-gray-900 border border-gray-300 placeholder-gray-400 focus:outline-none focus:ring-1 focus:ring-blue-500 focus:border-blue-500 transition duration-150 ease-in-out",
      placeholder := "search..."
    )
    searchInput.amend(
      searchInput.value --> styleSearch.writer
    )

    div(
      cls := "flex bg-gray-900 text-white py-4 px-8 items-center space-x-8",
      div(
        cls := "flex-shrink-0 -my-4 -mx-4",
        img(
          src := Site.thisVersionHref("/images/logo.svg"),
          cls := "w-10 h-10"
        )
      ),
      navTag(
        cls := "flex flex-1 md:flex-none space-x-4 items-center justify-start",
        span(
          Site.modules.take(1).map(moduleLink($page))
        ),
        span(
          cls := "text-gray-500 text-xs font-black",
          Site.laminextSiteVersion
        )
      ),
      navTag(
        cls := "hidden md:flex flex-1 space-x-4",
        div(
          cls := "flex flex-wrap justify-start items-center",
          Site.modules.drop(1).map(moduleLink($page))
        )
      ),
      div(
        cls := "hidden lg:block",
        img(src := "https://img.shields.io/maven-central/v/io.laminext/core_sjs1_2.13.svg?versionPrefix=0.16", alt := "latest version on maven central")
      ),
      div(
        cls := "hidden lg:block relative inline-block text-left",
        div(
          button.btn.sm.text.white(
            `type`        := "button",
            Icons.highlighter(svg.cls := "h-4 text-gray-300"),
            aria.hasPopup := true,
            aria.expanded <-- styleDropDownOpen.signal,
            onClick --> { _ => styleDropDownOpen.toggle() },
            Icons
              .chevronDown(
                svg.cls               := "-mr-1 ml-2 h-4 fill-current text-gray-300",
                svg.cls.toggle("hidden") <-- styleDropDownOpen.signal
              ),
            Icons
              .chevronUp(
                svg.cls               := "-mr-1 ml-2 h-4 fill-current text-gray-300",
                svg.cls.toggle("hidden") <-- !styleDropDownOpen.signal
              )
          )
        ),
        div(
          TW.transition(styleDropDownOpen.signal),
          cls := "origin-top-right absolute max-h-128 overflow-auto right-0 mt-2 w-56 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 z-20 p-2",
          div(
            cls              := "py-1",
            role             := "menu",
            aria.orientation := "vertical",
            aria.labelledBy  := "options-menu",
            div(
              cls := "mb-2",
              searchInput
            ),
            Styles.styles.map { styleName =>
              button(
                cls  := "block flex items-center space-x-2 w-full px-4 py-2 text-left text-gray-700 hover:bg-gray-200 hover:text-gray-900",
                onClick.mapTo(styleName) --> Styles.highlightStyle.setObserver,
                role := "menuitem",
                span(
                  cls := "flex-1",
                  styleName,
                ),
                child.maybe <-- Styles.highlightStyle.signal.valueIs(styleName).map {
                  if (_) {
                    Some(
                      span(
                        Icons.check(svg.cls := "h-6 text-green-500 fill-current")
                      )
                    )
                  } else {
                    None
                  }
                },
                cls.toggle("hidden") <-- styleSearch.signal.map(search => !styleName.contains(search))
              )
            }
          )
        )
      ),
      div(
        cls := "lg:hidden",
        button.btn.md.outline
          .white(
//            Icons.heroicons.menu(svg.cls := "hidden-before-css block w-5")
            "Menu",
            onClick.mapTo(
              Some(
                ModalContent(
                  div(
                    div(
                      cls := "flex justify-end py-4 px-8",
                      button.btn.md.outline
                        .white(
                          "Close",
                          onClick.mapTo(None) --> menuObserver
                        )
                    ),
                    PageNavigation($page, mobile = true),
                    div(
                      cls := "flex flex-wrap justify-start items-center p-4",
                      Site.modules.drop(1).map(moduleLink($page))
                    ),
                  ),
                  Some(menuObserver.contramap(_ => None))
                )
              )
            ) --> menuObserver
          )
      ),
      div(
        cls := "hidden lg:block",
        a(
          href := "https://github.com/tulz-app/laminext",
          rel  := "external",
          Icons.github(svg.cls := "h-6 text-gray-300")
        )
      )
    )
  }

  private def moduleLink(
    currentPage: Signal[Option[(SiteModule, Page)]]
  )(module: SiteModule)  =
    a(
      cls  := "border-b-2 px-2 border-transparent flex font-display tracking-wide",
      currentPage
        .map(_.exists(_._1.path == module.path))
        .classSwitch(
          "border-gray-300 text-white",
          "text-gray-300 hover:border-gray-300 hover:text-white "
        ),
      href := Site.thisVersionHref(s"/${module.path}"),
      module.title
    )

}

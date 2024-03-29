package io.laminext.site.layout

import io.laminext.site.ExampleModalContent
import io.laminext.site.Page
import io.laminext.site.Site
import io.laminext.site.SiteModule
import io.laminext.site.Styles
import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._
import com.raquo.laminar.nodes.ReactiveHtmlElement

object PageWrap {

  def apply(
    $page: Signal[Option[(SiteModule, Page)]],
    menuObserver: Observer[Option[ModalContent]]
  ): ReactiveHtmlElement.Base = {
    div(
      linkTag(
        rel := "stylesheet",
        href <-- Styles.highlightStyle.signal.map(s => Site.thisVersionHref(s"/stylesheets/highlightjs/${s}.css"))
      ),
      div(
        cls := "h-screen flex flex-col",
        PageHeader($page, menuObserver),
        noScriptTag(
          div(
            cls := "max-w-5xl border-l-4 border-red-400 bg-red-50 text-red-900 mx-auto p-4 font-condensed",
            "Your browser does not support JavaScript: some features of this site may not work properly."
          )
        ),
        div(
          cls := "flex-1 flex overflow-hidden",
          PageNavigation($page).amend(
            cls.toggle("hidden") <-- $page.optionMap(_._1).optionContains(Site.indexModule)
          ),
          div(
            cls := "flex-1 bg-gray-200 overflow-auto md:p-4",
            div(
              cls := "lg:container lg:mx-auto lg:max-w-4xl lg:p-8 p-4 bg-white min-h-full flex flex-col",
              child.maybe <-- $page.optionMap { case (_, page) => page.render() }
            )
          )
        ),
        PageFooter()
      ),
      TW.modal(ExampleModalContent.modalContent.signal),
    )
  }

}

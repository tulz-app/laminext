package app.tulz.laminext.site.pages

import app.tulz.laminext.site.components.DocumentationDisplay

object DocumentationPage {

  def apply(title: String, markdown: String): PageRender = page(title) { () =>
    DocumentationDisplay(title, markdown)
  }

}

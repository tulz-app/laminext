package app.tulz.laminext.site.pages

import app.tulz.laminext.site.components.DocumentationDisplay
import app.tulz.website.macros.FileToLiteral

object DocumentationPage {

  def apply(title: String, markdown: String): PageRender = page(title) {
    DocumentationDisplay(title, markdown)
  }

}

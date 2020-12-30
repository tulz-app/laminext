package io.laminext.site.pages

import io.laminext.site.components.DocumentationDisplay

object DocumentationPage {

  def apply(title: String, markdown: String): PageRender = page(title) { () =>
    DocumentationDisplay(title, markdown)
  }

}

package io.laminext.site.pages

import io.laminext.site.components.CodeExampleDisplay
import io.laminext.site.examples.CodeExample

object CodeExamplePage {

  def apply(example: CodeExample): PageRender = page(example.title) { () =>
    CodeExampleDisplay(example)
  }

}

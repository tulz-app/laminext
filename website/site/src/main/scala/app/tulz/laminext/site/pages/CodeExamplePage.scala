package app.tulz.laminext.site.pages

import app.tulz.laminext.site.components.CodeExampleDisplay
import app.tulz.laminext.site.examples.CodeExample

object CodeExamplePage {

  def apply(example: CodeExample[_]): PageRender = page(example.title) { () =>
    CodeExampleDisplay(example)
  }

}

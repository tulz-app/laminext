package io.laminext.site.pages

import com.raquo.laminar.api.L._
import io.laminext.site.components.CodeExampleDisplay
import io.laminext.site.examples.CodeExample

object CodeExamplePage {

  def apply(example: CodeExample): Element = page(example.title) {
    CodeExampleDisplay(example)
  }

}

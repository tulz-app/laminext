package io.laminext.markdown

import com.raquo.laminar.api.L.HtmlElement
import com.raquo.laminar.modifiers.Modifier
import io.laminext.markdown.markedjs.Marked

object UnsafeMarkdownReceiver {

  @inline def :=(markdown: String): Modifier[HtmlElement] = {
    new Modifier[HtmlElement] {
      override def apply(element: HtmlElement): Unit = element.ref.innerHTML = Marked.parse(markdown)
    }
  }

}

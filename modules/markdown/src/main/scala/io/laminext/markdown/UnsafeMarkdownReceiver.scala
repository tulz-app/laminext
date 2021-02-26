package io.laminext.markdown

import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L.HtmlElement
import io.laminext.markdown.markedjs.Marked

object UnsafeMarkdownReceiver {

  @inline def :=(markdown: String): Modifier[HtmlElement] = {
    new Modifier[HtmlElement] {
      override def apply(element: HtmlElement): Unit = element.ref.innerHTML = Marked.parse(markdown)
    }
  }

}

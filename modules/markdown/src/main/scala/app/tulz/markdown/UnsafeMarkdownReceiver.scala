package app.tulz.markdown

import app.tulz.markdown.markedjs.Marked
import com.raquo.laminar.api.L._
import com.raquo.domtypes.generic.Modifier

object UnsafeMarkdownReceiver {

  def :=(markdown: String): Modifier[HtmlElement] = {
    new Modifier[HtmlElement] {
      override def apply(element: HtmlElement): Unit = element.ref.innerHTML = Marked(markdown)
    }
  }

}

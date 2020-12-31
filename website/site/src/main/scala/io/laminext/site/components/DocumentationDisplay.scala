package io.laminext.site.components

import io.laminext.highlight.Highlight
import io.laminext.markdown.syntax._
import com.raquo.laminar.api.L._
import org.scalajs.dom.ext._

object DocumentationDisplay {

  def apply(title: String, markdown: String): Element = {
    div(
//      cls := "flex flex-col space-y-4",
//      div(
//        h1(
//          cls := "text-2xl font-bold text-cool-gray-900",
//          title
//        )
//      ),
      div(
        cls := "prose-custom max-w-none",
        unsafeMarkdown := markdown,
        onMountCallback { ctx =>
          ctx.thisNode.ref.querySelectorAll("pre > code").foreach { codeElement =>
            Highlight.highlightBlock(codeElement)
          }
        }
      )
    )
  }

}

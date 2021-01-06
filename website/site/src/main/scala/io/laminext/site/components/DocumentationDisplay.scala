package io.laminext.site.components

import io.laminext.highlight.Highlight
import io.laminext.markdown._
import com.raquo.laminar.api.L._
import org.scalajs.dom.ext._

object DocumentationDisplay {

  def apply(title: String, markdown: String): Element =
    div(
      cls := "prose-custom",
      unsafeMarkdown := markdown,
      onMountCallback { ctx =>
        ctx.thisNode.ref.querySelectorAll("pre > code").foreach { codeElement =>
          Highlight.highlightBlock(codeElement)
        }
      }
    )

}

package io.laminext.site.components

import io.laminext.highlight.Highlight
import com.raquo.laminar.api.L._
import io.laminext.syntax.markdown._
import io.laminext.site.TemplateVars
import org.scalajs.dom.ext._

object DocumentationDisplay {

  def apply(title: String, markdown: String): Element =
    div(
      cls := "space-y-4",
      h1(
        cls := "font-display text-3xl font-bold text-gray-900 tracking-wider md:hidden",
        title
      ),
      div(
        cls := "prose prose-blue max-w-none",
        unsafeMarkdown := TemplateVars(markdown),
        onMountCallback { ctx =>
          ctx.thisNode.ref.querySelectorAll("pre > code").foreach { codeElement =>
            Highlight.highlightBlock(codeElement)
          }
        }
      )
    )

}

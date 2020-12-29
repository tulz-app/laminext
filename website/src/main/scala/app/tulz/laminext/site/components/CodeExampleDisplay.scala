package app.tulz.laminext.site.components

import app.tulz.highlightjs.Highlight
import app.tulz.laminext.site.examples.CodeExample
import com.raquo.laminar.api.L._
import app.tulz.markdown._

object CodeExampleDisplay {

  def apply(example: CodeExample[_]): Element = {
    div(
      cls := "flex flex-col space-y-4",
      div(
        h1(
          cls := "text-2xl font-bold text-cool-gray-900",
          example.title
        )
      ),
      div(
        cls := "markdown",
        unsafeMarkdown := example.description
      ),
      div(
        cls := "space-y-2",
        h2(
          cls := "text-xl font-semibold text-cool-gray-900",
          "Source code:"
        ),
        pre(
          cls := "w-full max-h-144 overflow-auto shadow text-sm",
          onMountCallback { ctx =>
            Highlight.highlightBlock(ctx.thisNode.ref)
          },
          example.code.source
        )
      ),
      div(
        cls := "space-y-2",
        h2(
          cls := "text-xl font-semibold text-cool-gray-900",
          "Live demo:"
        ),
        div(
          cls := "border-4 border-dashed border-cool-gray-300 rounded-lg -m-2 p-4",
          onMountUnmountCallbackWithState(
            mount = ctx => render(ctx.thisNode.ref, example.code.value),
            unmount = (_, root: Option[RootNode]) => root.foreach(_.unmount())
          )
        )
      )
    )
  }

}

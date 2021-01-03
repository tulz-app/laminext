package io.laminext.site.components

import io.laminext.highlight.Highlight
import io.laminext.site.examples.CodeExample
import io.laminext.tailwind.theme
import com.raquo.laminar.api.L.{transition => _, _}
import io.laminext.tailwind.syntax._
import io.laminext.syntax.all._
import io.laminext.markdown._
import org.scalajs.dom.ext._

object CodeExampleDisplay {

  private def fixIndentation(src: String): String = {
    val lines =
      src
        .split('\n')
        .drop(1)
        .dropRight(1)

    val minIndent =
      lines
        .filterNot(_.trim.isEmpty)
        .map(_.takeWhile(_.isWhitespace))
        .map(_.length)
        .minOption
        .getOrElse(0)

    lines.map(_.drop(minIndent)).mkString("\n")
  }

  private val collapseTransition = theme.Transition.resize.customize(
    hidden = "max-h-32",
    enterFrom = "max-h-32",
    enterTo = "",
    leaveFrom = "",
    leaveTo = "max-h-32",
    onEnterFrom = el => {
      el.style.maxHeight = null
    },
    onEnterTo = el => {
      el.style.maxHeight = s"${el.scrollHeight}px"
    },
    onLeaveFrom = el => {
      el.style.maxHeight = s"${el.scrollHeight}px"
    },
    onLeaveTo = el => {
      el.style.maxHeight = null
    },
    onReset = (el, _) => {
      el.style.maxHeight = null
    }
  )

  def apply(example: CodeExample): Element = {
    val sourceCollapsed = storedBoolean(example.id, initial = false)
    div(
      cls := "flex flex-col space-y-4 mb-20",
      div(
        h1(
          cls := "text-2xl font-bold text-cool-gray-900",
          example.title
        )
      ),
      div(
        cls := "prose-custom",
        unsafeMarkdown := example.description,
        onMountCallback { ctx =>
          ctx.thisNode.ref.querySelectorAll("pre > code").foreach { codeElement =>
            Highlight.highlightBlock(codeElement)
          }
        }
      ),
      div(
        cls := "space-y-2",
        div(
          cls := "flex space-x-4",
          h2(
            cls := "flex-1 text-xl font-semibold text-cool-gray-900",
            "Source code:"
          ),
          span(
            cls := "flex-shrink-0",
            button.btn.sm.text.blue(
              child.text <-- sourceCollapsed.signal.map(if (_) "expand" else "collapse"),
              onClick --> { _ => sourceCollapsed.toggle() }
            )
          )
        ),
        div(
          cls := "overflow-hidden shadow relative",
          div(
            cls := "overflow-auto",
            transition(show = !sourceCollapsed.signal, collapseTransition),
            pre(
              cls := "w-full text-sm",
              onMountCallback { ctx =>
                Highlight.highlightBlock(ctx.thisNode.ref)
              },
              fixIndentation(example.code.source)
            )
          ),
          div(
            cls := "p-2 absolute left-0 right-0 bottom-0 bg-gradient-to-b from-cool-gray-500 to-cool-gray-600 opacity-75",
            button(
              cls := "w-full h-full text-center p-1 focus:outline-none focus:ring focus:ring-cool-gray-200 text-cool-gray-50 font-semibold",
              onClick.mapToUnit --> sourceCollapsed.toggleObserver,
              "expand"
            )
          ).visibleIf(sourceCollapsed.signal),
          div(
            cls := "p-2 bg-gradient-to-b from-cool-gray-500 to-cool-gray-600 opacity-75",
            button(
              cls := "w-full h-full text-center p-1 focus:outline-none focus:ring focus:ring-cool-gray-200 text-cool-gray-200 font-semibold",
              onClick.mapToUnit --> sourceCollapsed.toggleObserver,
              "collapse"
            )
          ).hiddenIf(sourceCollapsed.signal)
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
            mount = ctx => render(ctx.thisNode.ref, example.code.value()),
            unmount = (_, root: Option[RootNode]) => root.foreach(_.unmount())
          )
        )
      )
    )
  }

}

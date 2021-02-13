package io.laminext.site.components

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._
import io.laminext.syntax.markdown._
import io.laminext.highlight.Highlight
import io.laminext.site.examples.CodeExample
import io.laminext.tailwind.theme
import io.laminext.site.Styles
import io.laminext.site.TemplateVars
import org.scalajs.dom
import org.scalajs.dom.ext._
import org.scalajs.dom.html

import scala.scalajs.js

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

  private val collapseTransition = theme.Theme.current.transition.resize.customize(
    hidden = _ :+ "max-h-32",
    enterFrom = _ :+ "max-h-32",
    enterTo = _ => Seq.empty,
    leaveFrom = _ => Seq.empty,
    leaveTo = _ :+ "max-h-32",
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
    val dimContext      = storedBoolean("dim-context", initial = false)
    val hasContext      = example.code.source.contains("/* <focus> */")

    val codeNode = (dim: Boolean) => {
      val theCode = pre(
        cls := "w-full text-sm",
        fixIndentation(example.code.source)
      )
      div(
        theCode,
        onMountCallback { ctx =>
          Highlight.highlightBlock(ctx.thisNode.ref.childNodes.head)
          if (hasContext) {
            hideFocusMarkers(ctx.thisNode.ref.childNodes.head.asInstanceOf[html.Element])
            val _ = js.timers.setTimeout(0) {
              val updatedNode = setOpacityRecursively(theCode.ref, 0, dim)
              val _           = ctx.thisNode.ref.replaceChild(updatedNode, ctx.thisNode.ref.childNodes.head)
            }
          }
        }
      )
    }

    div(
      cls := "flex flex-col space-y-4 mb-20",
      div(
        h1(
          cls := "font-display text-3xl font-bold text-gray-900 tracking-wider",
          example.title
        )
      ),
      div(
        cls := "prose max-w-none",
        unsafeMarkdown := TemplateVars(example.description),
        onMountCallback { ctx =>
          ctx.thisNode.ref.querySelectorAll("pre > code").foreach { codeElement =>
            Highlight.highlightBlock(codeElement)
          }
        }
      ),
      div(
        cls := "space-y-2",
        div(
          cls := "flex space-x-4 items-center",
          h2(
            cls := "flex-1 text-xl font-semibold text-gray-900",
            "Source code:"
          ),
          when(hasContext) {
            label.btn.sm.text.blue(
              cls := "flex-shrink-0 flex space-x-1 items-center cursor-pointer",
              input(
                tpe := "checkbox",
                checked <-- dimContext.signal,
                inContext { el =>
                  el.events(onClick) --> { _ =>
                    dimContext.set(el.ref.checked)
                  }
                }
              ),
              span(
                "dim context"
              )
            )
          },
          span(
            cls := "flex-shrink-0",
            button.btn.sm.text.blue(
              cls := "w-20 justify-center",
              child.text <-- sourceCollapsed.signal.switch("expand", "collapse"),
              onClick --> sourceCollapsed.toggleObserver
            )
          )
        ),
        div(
          cls := "overflow-hidden shadow relative",
          div(
            cls := "overflow-auto",
            TW.transition(show = !sourceCollapsed.signal, collapseTransition),
            child <-- Styles.highlightStyle.signal.combineWithFn(dimContext.signal) { (_, dim) =>
              codeNode(dim)
            }
          ),
          div(
            cls := "p-2 absolute left-0 right-0 bottom-0 bg-gradient-to-b from-gray-500 to-gray-600 opacity-75",
            button(
              cls := "w-full h-full text-center p-1 focus:outline-none focus:ring focus:ring-gray-200 text-gray-50 font-semibold",
              onClick.mapToUnit --> sourceCollapsed.toggleObserver,
              "expand"
            )
          ).visibleIf(sourceCollapsed.signal),
          div(
            cls := "p-2 bg-gradient-to-b from-gray-500 to-gray-600 opacity-75",
            button(
              cls := "w-full h-full text-center p-1 focus:outline-none focus:ring focus:ring-gray-200 text-gray-200 font-semibold",
              onClick.mapToUnit --> sourceCollapsed.toggleObserver,
              "collapse"
            )
          ).hiddenIf(sourceCollapsed.signal)
        )
      ),
      div(
        cls := "space-y-2",
        h2(
          cls := "text-xl font-semibold text-gray-900",
          "Live demo:"
        ),
        div(
          cls := "border-4 border-dashed border-blue-400 bg-blue-300 text-blue-900 rounded-lg -m-2 p-4",
          onMountUnmountCallbackWithState(
            mount = ctx => render(ctx.thisNode.ref, example.code.value()),
            unmount = (_, root: Option[RootNode]) => root.foreach(_.unmount())
          )
        )
      )
    )
  }

  private def opaqueColor(color: String, opaque: Int, dim: Boolean): String = {
    if (opaque == 0 && dim) {
      if (color.startsWith("rgb(")) {
        color.replace("rgb(", "rgba(").replace(")", ", .4)")
      } else {
        color
      }
    } else {
      color
    }
  }

  private def setOpacityRecursively(element: html.Element, opaque: Int, dim: Boolean): dom.Node = {
    val elementColor = dom.window.getComputedStyle(element).color
    val newElement   = element.cloneNode(false).asInstanceOf[html.Element]
    if (opaque == 0) {
      newElement.style.color = opaqueColor(elementColor, opaque, dim)
    }

    var childrenOpaque = opaque
    val newChildNodes = element.childNodes.flatMap { child =>
      if (child.nodeName == "#text") {
        val span = dom.document.createElement("span").asInstanceOf[html.Element]
        span.innerText = child.textContent
        span.style.color = opaqueColor(elementColor, childrenOpaque, dim)
        Some(span)
      } else {
        if (child.innerText == "/* <focus> */") {
          childrenOpaque += 1
          None
        } else if (child.innerText == "/* </focus> */") {
          childrenOpaque -= 1
          None
        } else {
          Some(setOpacityRecursively(child.asInstanceOf[html.Element], childrenOpaque, dim))
        }
      }
    }
    newChildNodes.foreach(newElement.appendChild)
    newElement
  }

  private def hideFocusMarkers(element: html.Element): Unit =
    element.childNodes.foreach { child =>
      if (child.nodeName != "#text") {
        if (child.innerText == "/* <focus> */" || child.innerText == "/* </focus> */") {
          child.asInstanceOf[html.Element].style.display = "none"
        } else {
          hideFocusMarkers(child.asInstanceOf[html.Element])
        }
      }
    }

}

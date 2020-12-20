package app.tulz

import app.tulz.laminar.ext.AmAny
import app.tulz.laminar.ext.AmendedHtmlTag
import app.tulz.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonExpectSizeOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonFillOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonOutlineOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonTransparentOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindGroupButtonOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindOps
import app.tulz.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

package object tailwind {

  trait AmButtonExpectSize             extends AmAny
  trait AmButton                       extends AmAny
  trait AmButtonFillExpectColor        extends AmAny
  trait AmButtonOutlineExpectColor     extends AmAny
  trait AmButtonTransparentExpectColor extends AmAny
  trait AmGroupButton                  extends AmButton

  implicit def syntaxReactiveHtmlElementTailwind[T <: dom.html.Element](el: ReactiveHtmlElement[T]): ReactiveHtmlElementTailwindOps[T] = new ReactiveHtmlElementTailwindOps[T](el)

  implicit def syntaxReactiveSvgElementTailwind[T <: dom.svg.Element](el: ReactiveSvgElement[T]): ReactiveSvgElementTailwindOps[T] = new ReactiveSvgElementTailwindOps[T](el)

  implicit def syntaxHtmlTagTailwind[T <: dom.html.Element](tag: HtmlTag[T]): HtmlTagTailwindOps[T] = new HtmlTagTailwindOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonExpectSize[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonExpectSize]): HtmlTagTailwindButtonExpectSizeOps[T] =
    new HtmlTagTailwindButtonExpectSizeOps[T](tag)

  implicit def syntaxHtmlTagTailwindButton[T <: dom.html.Element, Am <: AmButton](tag: AmendedHtmlTag[T, Am]): HtmlTagTailwindButtonOps[T, Am] =
    new HtmlTagTailwindButtonOps[T, Am](tag)

  implicit def syntaxHtmlTagTailwindGroupButton[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmGroupButton]): HtmlTagTailwindGroupButtonOps[T] =
    new HtmlTagTailwindGroupButtonOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonFill[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonFillExpectColor]): HtmlTagTailwindButtonFillOps[T] =
    new HtmlTagTailwindButtonFillOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonOutline[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonOutlineExpectColor]): HtmlTagTailwindButtonOutlineOps[T] =
    new HtmlTagTailwindButtonOutlineOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonTransparent[T <: dom.html.Element](tag: AmendedHtmlTag[T, AmButtonTransparentExpectColor]): HtmlTagTailwindButtonTransparentOps[T] =
    new HtmlTagTailwindButtonTransparentOps[T](tag)

}

package app.tulz.tailwind

import app.tulz.laminar.ext.AmendedHtmlTag
import app.tulz.tailwind.ops.htmlelement.ReactiveHtmlElementTailwindOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonExpectSizeOrGroupOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonExpectsStyleOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonGroupExpectsPositionOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonGroupExpectsSizeOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindButtonGroupExpectsStyleOps
import app.tulz.tailwind.ops.htmltag.HtmlTagTailwindOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonFillOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonGroupFillOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonGroupOutlineOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonGroupTransparentOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonOutlineOps
import app.tulz.tailwind.ops.htmltag.style.HtmlTagTailwindButtonTransparentOps
import app.tulz.tailwind.ops.svgelement.ReactiveSvgElementTailwindOps
import com.raquo.laminar.builders.HtmlTag
import com.raquo.laminar.nodes.ReactiveHtmlElement
import com.raquo.laminar.nodes.ReactiveSvgElement
import org.scalajs.dom

trait ButtonSyntax {

  implicit def syntaxReactiveHtmlElementTailwind[T <: dom.html.Element](
    el: ReactiveHtmlElement[T]
  ): ReactiveHtmlElementTailwindOps[T] = new ReactiveHtmlElementTailwindOps[T](el)

  implicit def syntaxReactiveSvgElementTailwind[T <: dom.svg.Element](
    el: ReactiveSvgElement[T]
  ): ReactiveSvgElementTailwindOps[T] = new ReactiveSvgElementTailwindOps[T](el)

  implicit def syntaxHtmlTagTailwind[T <: dom.html.Element](
    tag: HtmlTag[T]
  ): HtmlTagTailwindOps[T] = new HtmlTagTailwindOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonExpectSizeOrGroup[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonExpectSizeOrGroup]
  ): HtmlTagTailwindButtonExpectSizeOrGroupOps[T] =
    new HtmlTagTailwindButtonExpectSizeOrGroupOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonGroupExpectPosition[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupExpectsPosition]
  ): HtmlTagTailwindButtonGroupExpectsPositionOps[T] =
    new HtmlTagTailwindButtonGroupExpectsPositionOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonGroupExpectsSize[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupExpectsSize]
  ): HtmlTagTailwindButtonGroupExpectsSizeOps[T] =
    new HtmlTagTailwindButtonGroupExpectsSizeOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonGroupExpectsStyle[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupExpectsStyle]
  ): HtmlTagTailwindButtonGroupExpectsStyleOps[T] =
    new HtmlTagTailwindButtonGroupExpectsStyleOps[T](tag)

  // ---

  implicit def syntaxHtmlTagTailwindButtonGroupFill[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupFillExpectColor]
  ): HtmlTagTailwindButtonGroupFillOps[T] =
    new HtmlTagTailwindButtonGroupFillOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonGroupOutlineOps[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupOutlineExpectColor]
  ): HtmlTagTailwindButtonGroupOutlineOps[T] =
    new HtmlTagTailwindButtonGroupOutlineOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonGroupTransparentOps[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonGroupTransparentExpectColor]
  ): HtmlTagTailwindButtonGroupTransparentOps[T] =
    new HtmlTagTailwindButtonGroupTransparentOps[T](tag)

  // ---

  implicit def syntaxHtmlTagTailwindButtonFill[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonFillExpectColor]
  ): HtmlTagTailwindButtonFillOps[T] =
    new HtmlTagTailwindButtonFillOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonOutline[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonOutlineExpectColor]
  ): HtmlTagTailwindButtonOutlineOps[T] =
    new HtmlTagTailwindButtonOutlineOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonTransparent[T <: dom.html.Element](
    tag: AmendedHtmlTag[T, AmButtonTransparentExpectColor]
  ): HtmlTagTailwindButtonTransparentOps[T] =
    new HtmlTagTailwindButtonTransparentOps[T](tag)

  implicit def syntaxHtmlTagTailwindButtonExpectsStyle[T <: dom.html.Element, Am <: AmButtonExpectsStyle](
    tag: AmendedHtmlTag[T, Am]
  ): HtmlTagTailwindButtonExpectsStyleOps[T, Am] =
    new HtmlTagTailwindButtonExpectsStyleOps[T, Am](tag)

}

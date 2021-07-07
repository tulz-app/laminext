package io.laminext.core

import com.raquo.laminar.api.L._

object UnsafeInnerHtmlReceiver {

  def :=[El <: Element](innerHtml: String): Modifier[El] =
    new Modifier[El] {
      override def apply(element: El): Unit = element.ref.innerHTML = innerHtml
    }

  def <--($innerHtml: Source[String]): Modifier[HtmlElement] =
    new Modifier[HtmlElement] {
      override def apply(parentNode: HtmlElement): Unit = {
        parentNode.amend(
          $innerHtml --> { newInnerHtml =>
            parentNode.ref.innerHTML = newInnerHtml
          }
        )
      }
    }

}

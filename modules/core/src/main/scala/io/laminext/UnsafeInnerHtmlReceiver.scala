package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.EventStream
import com.raquo.domtypes.generic.Modifier

object UnsafeInnerHtmlReceiver {

  def :=[El <: Element](innerHtml: String): Modifier[El] = {
    new Modifier[El] {
      override def apply(element: El): Unit = element.ref.innerHTML = innerHtml
    }
  }

  def <--($innerHtml: EventStream[String]): Modifier[HtmlElement] =
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

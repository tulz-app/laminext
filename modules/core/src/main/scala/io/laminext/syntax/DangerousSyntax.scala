package io.laminext.syntax

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Modifier
import io.laminext.core.UnsafeAppendRawChildModifier
import io.laminext.core.UnsafeInnerHtmlReceiver
import org.scalajs.dom

trait DangerousSyntax {

  @inline def unsafeAppendRawChild[El <: Element](
    child: dom.Node
  ): Modifier[El] = new UnsafeAppendRawChildModifier[El](child)

  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

}

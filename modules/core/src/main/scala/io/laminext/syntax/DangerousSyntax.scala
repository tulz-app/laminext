package io.laminext.syntax

import com.raquo.laminar.api.L._
import com.raquo.domtypes.generic.Modifier
import io.laminext.UnsafeAppendRawChildModifier
import io.laminext.UnsafeInnerHtmlReceiver

trait DangerousSyntax {

  @inline def unsafeAppendRawChild[El <: Element](
    child: org.scalajs.dom.raw.Node
  ): Modifier[El] = new UnsafeAppendRawChildModifier[El](child)

  val unsafeInnerHtml: UnsafeInnerHtmlReceiver.type = UnsafeInnerHtmlReceiver

}

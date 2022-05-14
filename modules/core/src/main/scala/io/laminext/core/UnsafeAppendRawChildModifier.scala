package io.laminext.core

import com.raquo.laminar.api.L._
import org.scalajs.dom

final class UnsafeAppendRawChildModifier[El <: Element](child: dom.Node) extends Modifier[El] {

  override def apply(element: El): Unit = {
    val _ = element.ref.appendChild(child)
  }

}

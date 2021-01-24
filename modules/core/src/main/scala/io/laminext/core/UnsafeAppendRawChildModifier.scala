package io.laminext.core

import com.raquo.laminar.api.L._

final class UnsafeAppendRawChildModifier[El <: Element](child: org.scalajs.dom.raw.Node) extends Modifier[El] {

  override def apply(element: El): Unit = {
    val _ = element.ref.appendChild(child)
  }

}

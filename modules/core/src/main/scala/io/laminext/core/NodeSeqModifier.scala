package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

final class NodeSeqModifier[El <: ReactiveElement[dom.Element]](
  seq: Modifier[El]*
) extends Modifier[El] {

  override def apply(el: El): Unit = {
    seq.foreach(_.apply(el))
  }

}

package app.tulz.laminext

import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom

class NodeSeqModifier[El <: ReactiveElement[dom.Element]](
  seq: Modifier[El]*
) extends Modifier[El] {

  override def apply(el: El): Unit = {
    seq.foreach(_.apply(el))
  }

}

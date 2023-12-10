package io.laminext.core

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement

private[laminext] object ConditionalChildInserter {

  def apply[El <: Element](
    condition: Observable[Boolean],
    child: => ChildNode.Base
  ): Inserter = L.child.maybe <-- condition.map(c => if (c) Some(child) else Option.empty)

}

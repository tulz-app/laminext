package app.tulz.laminext

import com.raquo.laminar.api.L._
import com.raquo.laminar.api.L
import com.raquo.laminar.nodes.ReactiveElement

object ConditionalChildInserter {

  def apply[El <: Element](
    condition: Observable[Boolean],
    child: => Child
  ): Inserter[ReactiveElement.Base] = L.child.maybe <-- condition.map(c => if (c) Some(child) else Option.empty)

}

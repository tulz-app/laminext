package io.laminext.ui.dnd

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.base.ComponentBase
import org.scalajs.dom

class DropZoneElement[+R <: dom.html.Element, A](
  val el: ReactiveHtmlElement[R],
  val enter: EventStream[Boolean],
  val leave: EventStream[Unit],
  val drop: EventStream[A],
  val over: StrictSignal[DraggingOver],
) extends ComponentBase[R]

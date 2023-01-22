package io.laminext.ui.dnd

import com.raquo.laminar.api.L._

class DropZoneState[A](
  val enter: EventStream[Boolean],
  val leave: EventStream[Unit],
  val drop: EventStream[A],
  val over: StrictSignal[DraggingOver],
)

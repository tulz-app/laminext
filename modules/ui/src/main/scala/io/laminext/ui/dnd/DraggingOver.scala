package io.laminext.ui.dnd

sealed trait DraggingOver extends Product with Serializable

object DraggingOver {

  case object Out                      extends DraggingOver
  case class Over(willAccept: Boolean) extends DraggingOver

}

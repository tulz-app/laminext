package io.laminext.ui

sealed private[ui] trait AnimationEvent extends Product with Serializable

private[ui] object AnimationEvent {

  case class Start(klass: String) extends AnimationEvent
  case object Reset               extends AnimationEvent

}

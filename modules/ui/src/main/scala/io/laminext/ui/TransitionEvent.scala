package io.laminext.ui

sealed private[ui] trait TransitionEvent extends Product with Serializable

private[ui] object TransitionEvent {

  case object EnterFrom  extends TransitionEvent
  case object EnterTo    extends TransitionEvent
  case object LeaveFrom  extends TransitionEvent
  case object LeaveTo    extends TransitionEvent
  case object Reset      extends TransitionEvent
  case object AfterReset extends TransitionEvent

}

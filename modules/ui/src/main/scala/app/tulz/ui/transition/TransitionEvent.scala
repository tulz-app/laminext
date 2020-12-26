package app.tulz.ui.transition

sealed private[transition] trait TransitionEvent extends Product with Serializable

private[transition] object TransitionEvent {

  case object EnterFrom extends TransitionEvent
  case object EnterTo   extends TransitionEvent
  case object LeaveFrom extends TransitionEvent
  case object LeaveTo   extends TransitionEvent
  case object Reset     extends TransitionEvent

}

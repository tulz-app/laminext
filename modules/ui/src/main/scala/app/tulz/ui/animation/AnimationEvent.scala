package app.tulz.ui.animation

sealed private[animation] trait AnimationEvent extends Product with Serializable

private[animation] object AnimationEvent {

  case class Start(klass: String) extends AnimationEvent
  case object Reset               extends AnimationEvent

}

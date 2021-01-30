package io.laminext.tailwind.theme

case class Animation(
  focusTransition: String
)

object Animation {

  val empty: Animation = Animation(focusTransition = "")

}

package io.laminext.tailwind.theme

case class Animation(
  focusTransition: String
)

object Animation {

  val default: Animation = Animation(
    focusTransition = "transition ease-in-out duration-150"
  )

}

package app.tulz.tailwind.theme

case class Animations(
  focusTransition: String
)

object Animations {

  val default: Animations = Animations(
    focusTransition = "transition ease-in-out duration-150"
  )

}

package io.laminext.tailwind.theme

case class Animation(
  focusTransition: BaseAndCustom
) {

  def customize(focusTransition: BaseAndCustom => BaseAndCustom = identity): Animation =
    this.copy(
      focusTransition = focusTransition(this.focusTransition)
    )

}

object Animation {

  val empty: Animation = Animation(focusTransition = BaseAndCustom.empty)

}

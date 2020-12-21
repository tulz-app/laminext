package app.tulz.tailwind.theme

final case class Theme(
  animation: Animation,
  button: Button,
  transition: Transition
) {

  def withButtonCustomClass(custom: String): Theme =
    this.copy(button = this.button.copy(custom = custom))

}

object Theme {

  val default: Theme = Theme(
    animation = Animation.default,
    button = Button.default,
    transition = Transition.default
  )

  private var _theme = default

  def current: Theme = _theme

  def setTheme(theme: Theme): Unit = {
    _theme = theme
  }

}

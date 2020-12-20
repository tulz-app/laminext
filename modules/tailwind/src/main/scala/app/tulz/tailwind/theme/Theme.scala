package app.tulz.tailwind.theme

final case class Theme(
  animations: Animations = Animations.default,
  button: Button = Button.default
)

object Theme {

  val default: Theme = Theme()

  private var _theme = default

  def current: Theme = _theme

  def setTheme(theme: Theme): Unit = {
    _theme = theme
  }

}

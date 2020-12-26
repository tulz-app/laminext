package app.tulz.tailwind.theme

final case class Theme(
  animation: Animation,
  button: Button,
  buttonGroup: BaseAndCustom,
  card: Card,
  transition: Transition,
  modal: Modal
) {

  def customize(
    animation: Animation => Animation = identity,
    button: Button => Button = identity,
    buttonGroup: BaseAndCustom => BaseAndCustom = identity,
    card: Card => Card = identity,
    transition: Transition => Transition = identity,
    modal: Modal => Modal = identity
  ): Theme = Theme(
    animation = animation(this.animation),
    button = button(this.button),
    buttonGroup = buttonGroup(this.buttonGroup),
    card = card(this.card),
    transition = transition(this.transition),
    modal = modal(this.modal)
  )

}

object Theme {

  val default: Theme = Theme(
    animation = Animation.default,
    button = Button.default,
    buttonGroup = BaseAndCustom(
      base = "relative z-0 inline-flex shadow-sm rounded-md"
    ),
    card = Card.default,
    transition = Transition.default,
    modal = Modal.default
  )

  private var _theme = default

  def current: Theme = _theme

  def setTheme(theme: Theme): Unit = {
    _theme = theme
  }

}

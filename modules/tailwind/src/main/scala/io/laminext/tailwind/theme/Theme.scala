package io.laminext.tailwind.theme

final case class Theme(
  animation: Animation,
  button: Button,
  buttonGroup: BaseAndCustom,
  card: Card,
  transition: Transition,
  modal: Modal,
  progressBar: ProgressBar,
  fileInput: FileInput
) {

  def customize(
    animation: Animation => Animation = identity,
    button: Button => Button = identity,
    buttonGroup: BaseAndCustom => BaseAndCustom = identity,
    card: Card => Card = identity,
    transition: Transition => Transition = identity,
    modal: Modal => Modal = identity,
    progressBar: ProgressBar => ProgressBar = identity,
    fileInput: FileInput => FileInput = identity
  ): Theme = Theme(
    animation = animation(this.animation),
    button = button(this.button),
    buttonGroup = buttonGroup(this.buttonGroup),
    card = card(this.card),
    transition = transition(this.transition),
    modal = modal(this.modal),
    progressBar = progressBar(this.progressBar),
    fileInput = fileInput(this.fileInput)
  )

}

object Theme {

  val empty: Theme = Theme(
    animation = Animation.empty,
    button = Button.empty,
    buttonGroup = BaseAndCustom.empty,
    card = Card.empty,
    transition = Transition.empty,
    modal = Modal.empty,
    progressBar = ProgressBar.default,
    fileInput = FileInput.empty
  )

  private var _theme = empty

  def current: Theme = _theme

  def setTheme(theme: Theme): Unit = {
    _theme = theme
  }

}

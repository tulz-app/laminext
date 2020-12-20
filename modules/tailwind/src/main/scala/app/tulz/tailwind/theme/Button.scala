package app.tulz.tailwind.theme

case class Button(
  common: String,
  disabled: String,
  single: String,
  size: ButtonSize,
  group: ButtonGroup,
  color: ButtonColor
)

object Button {

  val default: Button = Button(
    common =
      "inline-flex items-center border border-transparent focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
    disabled = "",
    single = "rounded-md",
    size = ButtonSize.default,
    group = ButtonGroup.default,
    color = ButtonColor.default
  )

}

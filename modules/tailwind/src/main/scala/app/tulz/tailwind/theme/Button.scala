package app.tulz.tailwind.theme

case class Button(
  common: String,
  disabled: String,
  custom: String,
  single: String,
  size: ButtonSize,
  group: ButtonGroup,
  color: ButtonColors
)

object Button {

  val default: Button = Button(
    common = "inline-flex items-center border border-transparent focus:outline-none",
    disabled = "disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
    custom = "",
    single = "rounded-md focus:ring-2 focus:ring-offset-2",
    size = ButtonSize.default,
    group = ButtonGroup.default,
    color = ButtonColors.default
  )

}

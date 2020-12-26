package app.tulz.tailwind.theme

case class Button(
  common: BaseAndCustom,
  disabled: String,
  single: BaseAndCustom,
  size: ButtonSize,
  group: GroupButton,
  color: ButtonColors
) {

  def customize(
    common: BaseAndCustom => BaseAndCustom = identity,
    disabled: String = this.disabled,
    single: BaseAndCustom => BaseAndCustom = identity,
    size: ButtonSize => ButtonSize = identity,
    group: GroupButton => GroupButton = identity,
    color: ButtonColors => ButtonColors = identity
  ): Button =
    Button(
      common = common(this.common),
      disabled = disabled,
      single = single(this.single),
      size = size(this.size),
      group = group(this.group),
      color = color(this.color)
    )

}

object Button {

  val default: Button = Button(
    common = BaseAndCustom(
      base = "inline-flex items-center border border-transparent focus:outline-none focus:ring-2 focus:ring-offset-2"
    ),
    disabled = "disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
    single = BaseAndCustom(
      base = "rounded-md"
    ),
    size = ButtonSize.default,
    group = GroupButton.default,
    color = ButtonColors.default
  )

}

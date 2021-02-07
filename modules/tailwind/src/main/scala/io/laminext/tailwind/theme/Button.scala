package io.laminext.tailwind.theme

case class Button(
  common: BaseAndCustom,
  disabled: BaseAndCustom,
  single: BaseAndCustom,
  size: ButtonSize,
  group: GroupButton,
  color: ButtonColors
) {

  def customize(
    common: BaseAndCustom => BaseAndCustom = identity,
    disabled: BaseAndCustom => BaseAndCustom = identity,
    single: BaseAndCustom => BaseAndCustom = identity,
    size: ButtonSize => ButtonSize = identity,
    group: GroupButton => GroupButton = identity,
    color: ButtonColors => ButtonColors = identity
  ): Button =
    Button(
      common = common(this.common),
      disabled = disabled(this.disabled),
      single = single(this.single),
      size = size(this.size),
      group = group(this.group),
      color = color(this.color)
    )

}

object Button {

  val empty: Button = Button(
    common = BaseAndCustom.empty,
    disabled = BaseAndCustom.empty,
    single = BaseAndCustom.empty,
    size = ButtonSize.empty,
    group = GroupButton.empty,
    color = ButtonColors.empty
  )

}

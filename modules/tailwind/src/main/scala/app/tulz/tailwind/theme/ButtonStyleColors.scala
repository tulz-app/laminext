package app.tulz.tailwind.theme

case class ButtonStyleColors(
  base: String = "",
  fill: String = "",
  outline: String = "",
  transparent: String = "",
  single: ButtonColorStyles = ButtonColorStyles.empty,
  group: ButtonColorStyles = ButtonColorStyles.empty
) {

  def customize(
    base: String = this.base,
    fill: String = this.fill,
    outline: String = this.outline,
    transparent: String = this.base,
    single: ButtonColorStyles => ButtonColorStyles = identity,
    group: ButtonColorStyles => ButtonColorStyles = identity
  ): ButtonStyleColors = ButtonStyleColors(
    base = base,
    fill = fill,
    outline = outline,
    transparent = transparent,
    single = single(this.single),
    group = group(this.group)
  )

}

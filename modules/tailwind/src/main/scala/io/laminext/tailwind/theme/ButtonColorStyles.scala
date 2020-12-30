package io.laminext.tailwind.theme

case class ButtonColorStyles(
  fill: String = "",
  outline: String = "",
  transparent: String = ""
) {

  def customize(
    fill: String = this.transparent,
    outline: String = this.transparent,
    transparent: String = this.transparent
  ): ButtonColorStyles = ButtonColorStyles(
    fill = fill,
    outline = outline,
    transparent = transparent
  )

}

object ButtonColorStyles {

  val empty: ButtonColorStyles = ButtonColorStyles()

}

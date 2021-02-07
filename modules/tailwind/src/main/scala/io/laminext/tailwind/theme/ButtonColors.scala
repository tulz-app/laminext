package io.laminext.tailwind.theme

case class ButtonColors(
  black: ButtonStyleColors,
  white: ButtonStyleColors,
  red: ButtonStyleColors,
  yellow: ButtonStyleColors,
  green: ButtonStyleColors,
  blue: ButtonStyleColors,
  indigo: ButtonStyleColors,
  purple: ButtonStyleColors,
  pink: ButtonStyleColors
) {

  def customize(
    black: ButtonStyleColors => ButtonStyleColors = identity,
    white: ButtonStyleColors => ButtonStyleColors = identity,
    red: ButtonStyleColors => ButtonStyleColors = identity,
    yellow: ButtonStyleColors => ButtonStyleColors = identity,
    green: ButtonStyleColors => ButtonStyleColors = identity,
    blue: ButtonStyleColors => ButtonStyleColors = identity,
    indigo: ButtonStyleColors => ButtonStyleColors = identity,
    purple: ButtonStyleColors => ButtonStyleColors = identity,
    pink: ButtonStyleColors => ButtonStyleColors = identity
  ): ButtonColors = ButtonColors(
    black = black(this.black),
    white = white(this.white),
    red = red(this.red),
    yellow = yellow(this.yellow),
    green = green(this.green),
    blue = blue(this.blue),
    indigo = indigo(this.indigo),
    purple = purple(this.purple),
    pink = pink(this.pink)
  )

}

object ButtonColors {

  val empty: ButtonColors = ButtonColors(
    black = ButtonStyleColors.empty,
    white = ButtonStyleColors.empty,
    red = ButtonStyleColors.empty,
    yellow = ButtonStyleColors.empty,
    green = ButtonStyleColors.empty,
    blue = ButtonStyleColors.empty,
    indigo = ButtonStyleColors.empty,
    purple = ButtonStyleColors.empty,
    pink = ButtonStyleColors.empty,
  )

}

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
    black = ButtonStyleColors(
      base = "focus:ring-gray-500",
      fill = "bg-black hover:bg-gray-900 text-gray-100 hover:white ",
      outline = "text-gray-800 hover:text-black border-gray-900 hover:bg-gray-100 focus:ring-gray-500",
      transparent = "text-gray-800 hover:black focus:ring-gray-500"
    ),
    white = ButtonStyleColors(
      base = "focus:ring-gray-500",
      fill = "bg-white hover:bg-gray-100 text-gray-900 hover:text-black ",
      outline = "text-gray-200 hover:text-white border-gray-100 hover:bg-gray-100",
      transparent = "text-gray-200 hover:text-white"
    ),
    red = ButtonStyleColors(
      base = "focus:ring-red-500",
      fill = "bg-red-600 hover:bg-red-500 text-gray-100 hover:white ",
      outline = "border-red-500 hover:bg-red-100 text-red-600 hover:text-red-500",
      transparent = "text-red-600 hover:text-red-500"
    ),
    yellow = ButtonStyleColors(
      base = "focus:ring-yellow-500",
      fill = "bg-yellow-600 hover:bg-yellow-500 text-gray-100 hover:white ",
      outline = "border-yellow-500 hover:bg-yellow-100 text-yellow-600 hover:text-yellow-500",
      transparent = "text-yellow-600 hover:text-yellow-500"
    ),
    green = ButtonStyleColors(
      base = "focus:ring-green-500",
      fill = "bg-green-600 hover:bg-green-500 text-gray-100 hover:white ",
      outline = "border-green-500 hover:bg-green-100 text-green-600 hover:text-green-500 ",
      transparent = "text-green-600 hover:text-green-500 "
    ),
    blue = ButtonStyleColors(
      base = "focus:ring-blue-500",
      fill = "bg-blue-600 hover:bg-blue-500 text-gray-100 hover:white",
      outline = "border-blue-500 hover:bg-blue-100 text-blue-600 hover:text-blue-500",
      transparent = "text-blue-600 hover:text-blue-500"
    ),
    indigo = ButtonStyleColors(
      base = "focus:ring-indigo-500",
      fill = "bg-indigo-600 hover:bg-indigo-500 text-gray-100 hover:white ",
      outline = "border-indigo-500 hover:bg-indigo-100 text-indigo-600 hover:text-indigo-500",
      transparent = "text-indigo-600 hover:text-indigo-500"
    ),
    purple = ButtonStyleColors(
      base = "focus:ring-purple-500",
      fill = "bg-purple-600 hover:bg-purple-500 text-gray-100 hover:white",
      outline = "border-purple-500 hover:bg-purple-100 text-purple-600 hover:text-purple-500",
      transparent = "text-purple-600 hover:text-purple-500"
    ),
    pink = ButtonStyleColors(
      base = "focus:ring-pink-500",
      fill = "bg-pink-600 hover:bg-pink-500 text-gray-100 hover:white ",
      outline = "border-pink-500 hover:bg-pink-100 text-pink-600 hover:text-pink-500 ",
      transparent = "text-pink-600 hover:text-pink-500"
    )
  )

}

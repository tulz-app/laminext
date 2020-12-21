package app.tulz.tailwind.theme

case class ButtonColors(
  black: ButtonKinsColorStyles,
  white: ButtonKinsColorStyles,
  red: ButtonKinsColorStyles,
  yellow: ButtonKinsColorStyles,
  green: ButtonKinsColorStyles,
  blue: ButtonKinsColorStyles,
  indigo: ButtonKinsColorStyles,
  purple: ButtonKinsColorStyles,
  pink: ButtonKinsColorStyles
)

object ButtonColors {

  val default: ButtonColors = ButtonColors(
    black = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-black hover:bg-gray-900 text-gray-100 hover:white focus:ring-gray-500",
        outline = "text-gray-800 hover:text-black border-gray-900 hover:bg-gray-100 focus:ring-gray-500",
        transparent = "text-gray-800 hover:black focus:ring-gray-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    white = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-white hover:bg-gray-100 text-gray-900 hover:text-black focus:ring-gray-500",
        outline = "text-gray-200 hover:text-white border-gray-100 hover:bg-gray-100 focus:ring-gray-200",
        transparent = "text-gray-200 hover:text-white focus:ring-gray-200"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    red = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-red-600 hover:bg-red-500 text-gray-100 hover:white focus:ring-red-500",
        outline = "border-red-500 hover:bg-red-100 text-red-600 hover:text-red-500 focus:ring-red-500",
        transparent = "text-red-600 hover:text-red-500 focus:ring-red-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    yellow = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-yellow-600 hover:bg-yellow-500 text-gray-100 hover:white focus:ring-yellow-500",
        outline = "border-yellow-500 hover:bg-yellow-100 text-yellow-600 hover:text-yellow-500 focus:ring-yellow-500",
        transparent = "text-yellow-600 hover:text-yellow-500 focus:ring-yellow-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    green = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-green-600 hover:bg-green-500 text-gray-100 hover:white focus:ring-green-500",
        outline = "border-green-500 hover:bg-green-100 text-green-600 hover:text-green-500 focus:ring-green-500",
        transparent = "text-green-600 hover:text-green-500 focus:ring-green-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    blue = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-blue-600 hover:bg-blue-500 text-gray-100 hover:white focus:ring-blue-500",
        outline = "border-blue-500 hover:bg-blue-100 text-blue-600 hover:text-blue-500 focus:ring-blue-500",
        transparent = "text-blue-600 hover:text-blue-500 focus:ring-blue-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    indigo = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-indigo-600 hover:bg-indigo-500 text-gray-100 hover:white focus:ring-indigo-500",
        outline = "border-indigo-500 hover:bg-indigo-100 text-indigo-600 hover:text-indigo-500 focus:ring-indigo-500",
        transparent = "text-indigo-600 hover:text-indigo-500 focus:ring-indigo-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    purple = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-purple-600 hover:bg-purple-500 text-gray-100 hover:white focus:ring-purple-500",
        outline = "border-purple-500 hover:bg-purple-100 text-purple-600 hover:text-purple-500 focus:ring-purple-500",
        transparent = "text-purple-600 hover:text-purple-500 focus:ring-purple-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    ),
    pink = ButtonKinsColorStyles(
      common = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      ),
      single = ButtonColorStyles(
        fill = "bg-pink-600 hover:bg-pink-500 text-gray-100 hover:white focus:ring-pink-500",
        outline = "border-pink-500 hover:bg-pink-100 text-pink-600 hover:text-pink-500 focus:ring-pink-500",
        transparent = "text-pink-600 hover:text-pink-500 focus:ring-pink-500"
      ),
      group = ButtonColorStyles(
        fill = "",
        outline = "",
        transparent = ""
      )
    )
  )

}

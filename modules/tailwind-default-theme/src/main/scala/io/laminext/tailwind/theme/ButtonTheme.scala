package io.laminext.tailwind.theme

object ButtonTheme {

  val buttonColors: ButtonColors = ButtonColors(
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

  val button = Button(
    common = BaseAndCustom(
      base = "inline-flex items-center border border-transparent focus:outline-none focus:ring-2 focus:ring-offset-2"
    ),
    disabled = "disabled:opacity-75 disabled:text-opacity-75 disabled:cursor-not-allowed",
    single = BaseAndCustom(
      base = "rounded-md"
    ),
    size = ButtonSize(
      tiny = "px-1.5 py-1 text-xs",
      xs = "px-2.5 py-1.5 text-xs",
      sm = "px-3 py-2 text-sm",
      md = "px-4 py-2 text-sm",
      lg = "px-4 py-2 text-base",
      xl = "px-6 py-3 text-base"
    ),
    group = GroupButton(
      common = BaseAndCustom(base = "focus:ring-1 focus:z-10"),
      first = BaseAndCustom(base = "rounded-l-md"),
      inner = BaseAndCustom(base = "rounded-none -mx-px"),
      last = BaseAndCustom(base = "rounded-r-md"),
      single = BaseAndCustom(base = "rounded-md")
    ),
    color = ButtonTheme.buttonColors
  )

}

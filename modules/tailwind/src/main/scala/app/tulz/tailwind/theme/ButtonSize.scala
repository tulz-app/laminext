package app.tulz.tailwind.theme

final case class ButtonSize(
  tiny: String,
  xs: String,
  sm: String,
  md: String,
  lg: String,
  xl: String
)

object ButtonSize {

  val default: ButtonSize = ButtonSize(
    tiny = "px-2.5 text-sm",
    xs = "px-2.5 py-1.5 text-sm leading-4",
    sm = "px-3 py-2 text-base leading-4",
    md = "px-4 py-2 text-lg leading-5",
    lg = "px-4 py-2 text-xl leading-6",
    xl = "px-6 py-3 text-2xl leading-6"
  )

}

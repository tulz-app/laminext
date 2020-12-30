package io.laminext.tailwind.theme

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
    tiny = "px-1.5 py-1 text-xs",
    xs = "px-2.5 py-1.5 text-xs",
    sm = "px-3 py-2 text-sm",
    md = "px-4 py-2 text-sm",
    lg = "px-4 py-2 text-base",
    xl = "px-6 py-3 text-base"
  )

}

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

  val empty: ButtonSize = ButtonSize(
    tiny = "",
    xs = "",
    sm = "",
    md = "",
    lg = "",
    xl = ""
  )

}

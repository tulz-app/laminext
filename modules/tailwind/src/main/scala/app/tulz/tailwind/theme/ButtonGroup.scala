package app.tulz.tailwind.theme

final case class ButtonGroup(
  common: String,
  custom: String,
  first: String,
  inner: String,
  last: String,
  single: String
)

object ButtonGroup {

  val default: ButtonGroup = ButtonGroup(
    common = "focus:ring-1 focus:z-10",
    custom = "",
    first = "rounded-l-md",
    inner = "rounded-none",
    last = "rounded-r-md",
    single = "rounded-md"
  )

}

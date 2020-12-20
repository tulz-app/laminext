package app.tulz.tailwind.theme

final case class ButtonGroup(
  common: String,
  first: String,
  inner: String,
  last: String,
  single: String
)

object ButtonGroup {

  val default: ButtonGroup = ButtonGroup(
    common = "",
    first = "rounded-l-md",
    inner = "rounded-none",
    last = "rounded-r-md",
    single = "rounded-md"
  )

}

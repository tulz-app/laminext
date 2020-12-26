package app.tulz.tailwind.theme

import app.tulz.laminar.ext._

final case class BaseAndCustom(
  base: String,
  custom: String = ""
) {

  def custom(custom: String): BaseAndCustom =
    if (custom == this.custom) {
      this
    } else {
      this.copy(custom = custom)
    }

  val classes: String = classJoin(base, custom)

}

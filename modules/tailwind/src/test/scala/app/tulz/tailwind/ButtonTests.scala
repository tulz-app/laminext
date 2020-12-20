package app.tulz.tailwind

import app.tulz.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec

class ButtonTests extends UnitSpec {

  it(".btn") {
    button.btn ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.single,
        Theme.default.animations.focusTransition
      ).mkString(" ")
  }

  it(".btn.sm") {
    button.btn.sm ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.single,
        Theme.default.animations.focusTransition,
        Theme.default.button.size.sm
      ).mkString(" ")
  }

  it(".btn.md.fill.blue") {
    button.btn.md.fill.red ====
      Seq(
        "testing diff",
        Theme.default.button.common,
        Theme.default.button.single,
        Theme.default.animations.focusTransition,
        Theme.default.button.size.md,
        Theme.default.button.color.blue.fill
      ).mkString(" ")
  }

}

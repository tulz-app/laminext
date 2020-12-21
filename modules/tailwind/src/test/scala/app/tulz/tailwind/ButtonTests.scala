package app.tulz.tailwind

import app.tulz.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import org.scalatest.BeforeAndAfterEach

class ButtonTests extends UnitSpec with BeforeAndAfterEach {

  override def afterEach(): Unit = {
    Theme.setTheme(Theme.default)
  }

  it(".btn") {
    button.btn ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.button.single,
        Theme.default.animation.focusTransition
      ).mkString(" ")
  }

  it(".btn.sm") {
    button.btn.sm ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.button.single,
        Theme.default.animation.focusTransition,
        Theme.default.button.size.sm
      ).mkString(" ")
  }

  it(".btn.md.fill.blue") {
    button.btn.md.fill.blue ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.button.single,
        Theme.default.animation.focusTransition,
        Theme.default.button.size.md,
        Theme.default.button.color.blue.common.fill,
        Theme.default.button.color.blue.single.fill
      ).mkString(" ")
  }

  it(".btn.md.fill.blue.custom") {
    Theme.setTheme(
      Theme.default.withButtonCustomClass("custom-1 custom-2")
    )
    button.btn.md.fill.blue ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.button.single,
        Theme.default.animation.focusTransition,
        Theme.default.button.size.md,
        Theme.default.button.color.blue.common.fill,
        Theme.default.button.color.blue.single.fill,
        "custom-1 custom-2"
      ).mkString(" ")
  }

  it(".btn.group.first") {
    button.btn.group.first ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common,
        Theme.default.button.group.custom,
        Theme.default.button.group.first
      ).mkString(" ")
  }

  it(".btn.group.first.lg.fill.blue") {
    button.btn.group.first.lg.fill.blue ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common,
        Theme.default.button.group.custom,
        Theme.default.button.group.first,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.common.fill,
        Theme.default.button.color.blue.group.fill
      ).mkString(" ")
  }

  it(".btn.group.inner.xl.outline.red") {
    button.btn.group.inner.xl.outline.red ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common,
        Theme.default.button.group.custom,
        Theme.default.button.group.inner,
        Theme.default.button.size.xl,
        Theme.default.button.color.red.common.outline,
        Theme.default.button.color.red.group.outline
      ).mkString(" ")
  }

  it(".btn.group.last.xs.text.indigo") {
    button.btn.group.last.xs.text.indigo ====
      Seq(
        Theme.default.button.common,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common,
        Theme.default.button.group.custom,
        Theme.default.button.group.last,
        Theme.default.button.size.xs,
        Theme.default.button.color.indigo.common.fill,
        Theme.default.button.color.indigo.group.fill
      ).mkString(" ")
  }

}

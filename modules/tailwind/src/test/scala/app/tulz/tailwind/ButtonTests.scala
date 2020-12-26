package app.tulz.tailwind

import theme.Theme
import com.raquo.laminar.api.L._
import app.tulz.laminar.ext._
import com.raquo.laminar.utils.UnitSpec
import org.scalatest.BeforeAndAfterEach

class ButtonTests extends UnitSpec with BeforeAndAfterEach {

  override def afterEach(): Unit = {
    Theme.setTheme(Theme.default)
  }

  it(".btn.md.fill.blue") {
    button.btn.md.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.button.single.classes,
        Theme.default.animation.focusTransition,
        Theme.default.button.size.md,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.single.fill
      )

  }

  it(".btn.md.fill.blue custom theme") {
    Theme.setTheme(
      Theme.default.customize(
        button = _.customize(common = _.custom("custom-1 custom-2"))
      )
    )
    button.btn.md.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.button.single.classes,
        Theme.default.animation.focusTransition,
        Theme.default.button.size.md,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.single.fill,
        "custom-1 custom-2"
      )
  }

  it(".btn.group.first.lg.fill.blue") {
    button.btn.group.first.lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.first.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.last.lg.fill.blue") {
    button.btn.group.last.lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.last.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.inner.lg.fill.blue") {
    button.btn.group.inner.lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.inner.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.single.lg.fill.blue") {
    button.btn.group.single.lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.single.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(0, 3).lg.fill.blue") {
    button.btn.group.nth(0, 3).lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.first.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(1, 3).lg.fill.blue") {
    button.btn.group.nth(1, 3).lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.inner.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(2, 3).lg.fill.blue") {
    button.btn.group.nth(2, 3).lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.last.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(0, 1).lg.fill.blue") {
    button.btn.group.nth(0, 1).lg.fill.blue ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.single.classes,
        Theme.default.button.size.lg,
        Theme.default.button.color.blue.base,
        Theme.default.button.color.blue.fill,
        Theme.default.button.color.blue.group.fill
      )
  }

  it(".btn.group.inner.xl.outline.red") {
    button.btn.group.inner.xl.outline.red ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.inner.classes,
        Theme.default.button.size.xl,
        Theme.default.button.color.red.base,
        Theme.default.button.color.red.outline,
        Theme.default.button.color.red.group.outline
      )
  }

  it(".btn.group.last.xs.text.indigo") {
    button.btn.group.last.xs.text.indigo ====
      classJoin(
        Theme.default.button.common.classes,
        Theme.default.button.disabled,
        Theme.default.animation.focusTransition,
        Theme.default.button.group.common.classes,
        Theme.default.button.group.last.classes,
        Theme.default.button.size.xs,
        Theme.default.button.color.indigo.base,
        Theme.default.button.color.indigo.transparent,
        Theme.default.button.color.indigo.group.transparent
      )
  }

}

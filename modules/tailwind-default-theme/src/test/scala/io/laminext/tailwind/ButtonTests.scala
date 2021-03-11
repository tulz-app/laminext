package io.laminext.tailwind

import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import io.laminext.syntax.tailwind._
import io.laminext.tailwind.theme.DefaultTheme
import io.laminext.tailwind.theme.Theme
import io.laminext.util.ClassJoin
import org.scalatest.BeforeAndAfter

class ButtonTests extends UnitSpec with BeforeAndAfter {

  before {
    Theme.setTheme(DefaultTheme.theme)
  }

  it(".btn.md.fill.blue") {
    button.btn.md.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.button.single.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.size.md.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.single.fill
      )

  }

  it(".btn.md.fill.blue custom theme") {
    Theme.setTheme(
      DefaultTheme.theme.customize(
        button = _.customize(common = _.custom("custom-1 custom-2"))
      )
    )
    button.btn.md.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.button.single.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.size.md.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.single.fill,
        "custom-1 custom-2"
      )
  }

  it(".btn.group.first.lg.fill.blue") {
    button.btn.group.first.lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.first.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.last.lg.fill.blue") {
    button.btn.group.last.lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.last.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.inner.lg.fill.blue") {
    button.btn.group.inner.lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.inner.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.single.lg.fill.blue") {
    button.btn.group.single.lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.single.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(0, 3).lg.fill.blue") {
    button.btn.group.nth(0, 3).lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.first.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(1, 3).lg.fill.blue") {
    button.btn.group.nth(1, 3).lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.inner.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(2, 3).lg.fill.blue") {
    button.btn.group.nth(2, 3).lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.last.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.nth(0, 1).lg.fill.blue") {
    button.btn.group.nth(0, 1).lg.fill.blue ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.single.classes,
        DefaultTheme.theme.button.size.lg.classes,
        DefaultTheme.theme.button.color.blue.base,
        DefaultTheme.theme.button.color.blue.fill,
        DefaultTheme.theme.button.color.blue.group.fill
      )
  }

  it(".btn.group.inner.xl.outline.red") {
    button.btn.group.inner.xl.outline.red ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.inner.classes,
        DefaultTheme.theme.button.size.xl.classes,
        DefaultTheme.theme.button.color.red.base,
        DefaultTheme.theme.button.color.red.outline,
        DefaultTheme.theme.button.color.red.group.outline
      )
  }

  it(".btn.group.last.xs.text.indigo") {
    button.btn.group.last.xs.text.indigo ====
      ClassJoin(
        DefaultTheme.theme.button.common.classes,
        DefaultTheme.theme.button.disabled.classes,
        DefaultTheme.theme.animation.focusTransition.classes,
        DefaultTheme.theme.button.group.common.classes,
        DefaultTheme.theme.button.group.last.classes,
        DefaultTheme.theme.button.size.xs.classes,
        DefaultTheme.theme.button.color.indigo.base,
        DefaultTheme.theme.button.color.indigo.transparent,
        DefaultTheme.theme.button.color.indigo.group.transparent
      )
  }

}

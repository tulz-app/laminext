package io.laminext.tailwind

import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import io.laminext.syntax.tailwind._
import io.laminext.tailwind.theme.DefaultTheme
import io.laminext.tailwind.theme.Theme
import io.laminext.util.ClassJoin
import org.scalatest.BeforeAndAfter

class CardTests extends UnitSpec with BeforeAndAfter {

  before {
    Theme.setTheme(DefaultTheme.theme)
  }

  it(".card.wrap") {
    div.card.wrap ====
      ClassJoin(
        DefaultTheme.theme.card.wrap.classes
      )
  }

  it(".card.header") {
    div.card.header ====
      ClassJoin(
        DefaultTheme.theme.card.header.classes
      )
  }

  it(".card.body") {
    div.card.body ====
      ClassJoin(
        DefaultTheme.theme.card.body.classes
      )
  }

  it(".card.footer") {
    div.card.footer ====
      ClassJoin(
        DefaultTheme.theme.card.footer.classes
      )
  }

  it(".card.title") {
    div.card.title ====
      ClassJoin(
        DefaultTheme.theme.card.title.classes
      )
  }

}

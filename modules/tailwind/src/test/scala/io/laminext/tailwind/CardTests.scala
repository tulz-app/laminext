package io.laminext.tailwind

import syntax._
import io.laminext.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import io.laminext.util.ClassJoin
import org.scalatest.BeforeAndAfterEach

class CardTests extends UnitSpec with BeforeAndAfterEach {

  override def afterEach(): Unit = {
    Theme.setTheme(Theme.default)
  }

  it(".card.wrap") {
    div.card.wrap ====
      ClassJoin(
        "bg-white sm:shadow sm:rounded-lg"
//        Theme.default.card.header.classes
      )
  }

  it(".card.header") {
    div.card.header ====
      ClassJoin(
        Theme.default.card.header.classes
      )
  }

  it(".card.body") {
    div.card.body ====
      ClassJoin(
        Theme.default.card.body.classes
      )
  }

  it(".card.footer") {
    div.card.footer ====
      ClassJoin(
        Theme.default.card.footer.classes
      )
  }

  it(".card.title") {
    div.card.title ====
      ClassJoin(
        Theme.default.card.title.classes
      )
  }

}

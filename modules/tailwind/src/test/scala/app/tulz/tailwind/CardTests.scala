package app.tulz.tailwind

import app.tulz.laminar.ext._
import app.tulz.tailwind.theme.Theme
import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import org.scalatest.BeforeAndAfterEach

class CardTests extends UnitSpec with BeforeAndAfterEach {

  override def afterEach(): Unit = {
    Theme.setTheme(Theme.default)
  }

  it(".card.wrap") {
    div.card.wrap ====
      classJoin(
        "bg-white sm:shadow sm:rounded-lg"
//        Theme.default.card.header.classes
      )
  }

  it(".card.header") {
    div.card.header ====
      classJoin(
        Theme.default.card.header.classes
      )
  }

  it(".card.body") {
    div.card.body ====
      classJoin(
        Theme.default.card.body.classes
      )
  }

  it(".card.footer") {
    div.card.footer ====
      classJoin(
        Theme.default.card.footer.classes
      )
  }

  it(".card.title") {
    div.card.title ====
      classJoin(
        Theme.default.card.title.classes
      )
  }

}

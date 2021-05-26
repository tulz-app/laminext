package io.laminext.tailwind

import com.raquo.laminar.api.L._
import com.raquo.laminar.utils.UnitSpec
import io.laminext.ui._
import io.laminext.util.ClassJoin
import org.scalatest.BeforeAndAfter

class CardTests extends UnitSpec with BeforeAndAfter {

  it(".card.wrap") {
    div.card.wrap ==== "card-wrap"
  }

  it(".card.header") {
    div.card.header ==== "card-header"
  }

  it(".card.body") {
    div.card.body ==== "card-body"
  }

  it(".card.footer") {
    div.card.footer ==== "card-footer"
  }

  it(".card.title") {
    div.card.title ==== "card-title"
  }

}

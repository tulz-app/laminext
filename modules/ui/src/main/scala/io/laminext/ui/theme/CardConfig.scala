package io.laminext.ui.theme

import com.raquo.laminar.api.L._

case class CardConfig(
  wrap: Mod[HtmlElement],
  body: Mod[HtmlElement],
  header: Mod[HtmlElement],
  footer: Mod[HtmlElement],
  title: Mod[HtmlElement],
)

object CardConfig {

  val empty: CardConfig = CardConfig(
    wrap = emptyMod,
    body = emptyMod,
    header = emptyMod,
    footer = emptyMod,
    title = emptyMod,
  )

}

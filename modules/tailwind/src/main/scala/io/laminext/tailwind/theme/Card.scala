package io.laminext.tailwind.theme

final case class Card(
  wrap: BaseAndCustom,
  header: BaseAndCustom,
  body: BaseAndCustom,
  footer: BaseAndCustom,
  title: BaseAndCustom
) {

  def custom(
    wrap: String = this.wrap.custom,
    header: String = this.header.custom,
    body: String = this.body.custom,
    footer: String = this.footer.custom,
    title: String = this.title.custom
  ): Card =
    this.copy(
      wrap = this.wrap.custom(wrap),
      header = this.header.custom(header),
      body = this.body.custom(body),
      footer = this.footer.custom(footer),
      title = this.title.custom(title)
    )

}

object Card {

  val empty: Card = Card(
    wrap = BaseAndCustom.empty,
    header = BaseAndCustom.empty,
    body = BaseAndCustom.empty,
    footer = BaseAndCustom.empty,
    title = BaseAndCustom.empty
  )

}

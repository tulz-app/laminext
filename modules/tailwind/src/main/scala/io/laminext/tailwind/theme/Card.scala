package io.laminext.tailwind.theme

final case class Card(
  wrap: BaseAndCustom,
  header: BaseAndCustom,
  body: BaseAndCustom,
  footer: BaseAndCustom,
  title: BaseAndCustom
) {

  def customize(
    wrap: BaseAndCustom => BaseAndCustom = identity,
    header: BaseAndCustom => BaseAndCustom = identity,
    body: BaseAndCustom => BaseAndCustom = identity,
    footer: BaseAndCustom => BaseAndCustom = identity,
    title: BaseAndCustom => BaseAndCustom = identity
  ): Card =
    this.copy(
      wrap = wrap(this.wrap),
      header = header(this.header),
      body = body(this.body),
      footer = footer(this.footer),
      title = title(this.title)
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

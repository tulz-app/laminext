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

  val default: Card = Card(
    wrap = BaseAndCustom(
      base = "bg-white sm:shadow sm:rounded-lg"
    ),
    header = BaseAndCustom(
      base = "px-4 py-3 sm:px-6 border-b border-gray-200"
    ),
    body = BaseAndCustom(
      base = "p-4 md:px-6 md:rounded-lg"
    ),
    footer = BaseAndCustom(
      base = "px-4 py-3 sm:px-6 border-t border-gray-200"
    ),
    title = BaseAndCustom(
      base = "text-lg md:text-xl font-medium tracking-wide text-gray-700"
    )
  )

}

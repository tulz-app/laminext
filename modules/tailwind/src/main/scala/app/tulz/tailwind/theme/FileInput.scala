package app.tulz.tailwind.theme

final case class FileInput(
  selecting: BaseAndCustom,
  invalid: BaseAndCustom,
  ready: BaseAndCustom
) {

  def customize(
    selecting: BaseAndCustom => BaseAndCustom = identity,
    invalid: BaseAndCustom => BaseAndCustom = identity,
    ready: BaseAndCustom => BaseAndCustom = identity
  ): FileInput = FileInput(
    selecting = selecting(this.selecting),
    invalid = invalid(this.invalid),
    ready = ready(this.ready)
  )

}

object FileInput {

  def default(button: Button): FileInput = FileInput(
    selecting = BaseAndCustom(
      base = Seq(
        button.color.blue.base,
        button.color.blue.outline,
        button.color.blue.single.fill
      ).mkString(" ")
    ),
    invalid = BaseAndCustom(
      base = Seq(
        button.color.red.base,
        button.color.red.outline,
        button.color.red.single.fill
      ).mkString(" ")
    ),
    ready = BaseAndCustom(
      base = Seq(
        button.color.green.base,
        button.color.green.fill,
        button.color.green.single.fill
      ).mkString(" ")
    )
  )

}

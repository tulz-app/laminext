package io.laminext.tailwind.theme

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

  val empty: FileInput = FileInput(
    selecting = BaseAndCustom.empty,
    invalid = BaseAndCustom.empty,
    ready = BaseAndCustom.empty
  )

}

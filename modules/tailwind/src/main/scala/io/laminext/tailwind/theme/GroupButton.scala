package io.laminext.tailwind.theme

final case class GroupButton(
  common: BaseAndCustom,
  first: BaseAndCustom,
  inner: BaseAndCustom,
  last: BaseAndCustom,
  single: BaseAndCustom
) {

  def customize(
    common: BaseAndCustom => BaseAndCustom = identity,
    first: BaseAndCustom => BaseAndCustom = identity,
    inner: BaseAndCustom => BaseAndCustom = identity,
    last: BaseAndCustom => BaseAndCustom = identity,
    single: BaseAndCustom => BaseAndCustom = identity
  ): GroupButton = GroupButton(
    common = common(this.common),
    first = first(this.first),
    inner = inner(this.inner),
    last = last(this.last),
    single = single(this.single)
  )

  def custom(custom: String): GroupButton = this.copy(
    common = common.custom(custom)
  )

}

object GroupButton {

  val empty: GroupButton = GroupButton(
    common = BaseAndCustom.empty,
    first = BaseAndCustom.empty,
    inner = BaseAndCustom.empty,
    last = BaseAndCustom.empty,
    single = BaseAndCustom.empty
  )

}

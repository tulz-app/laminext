package io.laminext.tailwind.theme

final case class ButtonSize(
  tiny: BaseAndCustom,
  xs: BaseAndCustom,
  sm: BaseAndCustom,
  md: BaseAndCustom,
  lg: BaseAndCustom,
  xl: BaseAndCustom
) {

  def customize(
    tiny: BaseAndCustom => BaseAndCustom = identity,
    xs: BaseAndCustom => BaseAndCustom = identity,
    sm: BaseAndCustom => BaseAndCustom = identity,
    md: BaseAndCustom => BaseAndCustom = identity,
    lg: BaseAndCustom => BaseAndCustom = identity,
    xl: BaseAndCustom => BaseAndCustom = identity
  ): ButtonSize = this.copy(
    tiny = tiny(this.tiny),
    xs = xs(this.xs),
    sm = sm(this.sm),
    md = md(this.md),
    lg = lg(this.lg),
    xl = xl(this.xl)
  )

}

object ButtonSize {

  val empty: ButtonSize = ButtonSize(
    tiny = BaseAndCustom.empty,
    xs = BaseAndCustom.empty,
    sm = BaseAndCustom.empty,
    md = BaseAndCustom.empty,
    lg = BaseAndCustom.empty,
    xl = BaseAndCustom.empty
  )

}

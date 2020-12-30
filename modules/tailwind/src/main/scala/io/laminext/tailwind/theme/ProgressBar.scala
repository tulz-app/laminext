package io.laminext.tailwind.theme

case class ProgressBar(
  wrap: BaseAndCustom,
  progress: BaseAndCustom
) {

  def customize(
    wrap: BaseAndCustom => BaseAndCustom = identity,
    progress: BaseAndCustom => BaseAndCustom = identity
  ): ProgressBar = ProgressBar(
    wrap = wrap(this.wrap),
    progress = progress(this.progress)
  )

}

object ProgressBar {

  val default: ProgressBar = ProgressBar(
    wrap = BaseAndCustom(
      base = "w-full rounded bg-green-200"
    ),
    progress = BaseAndCustom(
      base = "bg-green-500 h-2 rounded"
    )
  )

}

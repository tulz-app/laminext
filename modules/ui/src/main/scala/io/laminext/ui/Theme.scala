package io.laminext.ui

import io.laminext.ui.theme.CardConfig
import io.laminext.ui.theme.FileInputConfig
import io.laminext.ui.theme.ModalConfig
import io.laminext.ui.theme.ProgressBarConfig

case class Theme(
  modal: ModalConfig,
  progressBar: ProgressBarConfig,
  fileInput: FileInputConfig,
  card: CardConfig,
)

object Theme {

  private var _default = Theme(
    modal = ModalConfig.default,
    progressBar = ProgressBarConfig.empty,
    fileInput = FileInputConfig.empty,
    card = CardConfig.empty,
  )

  @inline def default: Theme = _default

  def update(f: Theme => Theme): Unit = {
    _default = f(_default)
  }

  def set(t: Theme): Unit = {
    _default = t
  }

}

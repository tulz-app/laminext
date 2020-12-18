package app.tulz.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr

object ControlBarOptions {

  def apply(
    volumePanel: UndefOr[VolumePanelOptions] = js.undefined,
    fullscreenToggle: UndefOr[Boolean] = js.undefined,
    pictureInPictureToggle: UndefOr[Boolean] = js.undefined
  ): ControlBarOptions = {
    js.Dynamic
      .literal(
        volumePanel = volumePanel,
        fullscreenToggle = fullscreenToggle,
        pictureInPictureToggle = pictureInPictureToggle
      ).asInstanceOf[ControlBarOptions]
  }

}

@js.native
trait ControlBarOptions extends ComponentOptions {
  val volumePanel: UndefOr[VolumePanelOptions]
  val fullscreenToggle: UndefOr[Boolean]
  val pictureInPictureToggle: UndefOr[Boolean]
}

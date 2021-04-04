package io.laminext.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr

object ControlBarOptions {

  def apply(
    volumePanel: UndefOr[VolumePanelOptions] = js.undefined,
    fullscreenToggle: UndefOr[Boolean] = js.undefined,
    pictureInPictureToggle: UndefOr[Boolean] = js.undefined
  ): ControlBarOptions = {
    val obj = js.Object().asInstanceOf[js.Dynamic]
    volumePanel.foreach(obj.volumePanel = _)
    fullscreenToggle.foreach(obj.fullscreenToggle = _)
    pictureInPictureToggle.foreach(obj.pictureInPictureToggle = _)
    obj.asInstanceOf[ControlBarOptions]
  }

}

@js.native
trait ControlBarOptions extends ComponentOptions {
  val volumePanel: UndefOr[VolumePanelOptions]
  val fullscreenToggle: UndefOr[Boolean]
  val pictureInPictureToggle: UndefOr[Boolean]
}

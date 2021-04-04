package io.laminext.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr

object VolumePanelOptions {

  def apply(
    inline: UndefOr[Boolean] = js.undefined,
    volumeControl: UndefOr[VolumeControlOptions] = js.undefined
  ): VolumePanelOptions = {
    val obj = js.Object().asInstanceOf[js.Dynamic]
    inline.foreach(obj.inline = _)
    volumeControl.foreach(obj.volumeControl = _)
    obj.asInstanceOf[VolumePanelOptions]
  }

}

@js.native
trait VolumePanelOptions extends ComponentOptions {
  val inline: UndefOr[Boolean]
  val volumeControl: UndefOr[VolumeControlOptions]
}

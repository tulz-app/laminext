package app.tulz.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr

object VolumePanelOptions {

  def apply(
    inline: UndefOr[Boolean] = js.undefined,
    volumeControl: UndefOr[VolumeControlOptions] = js.undefined
  ): VolumePanelOptions = {
    js.Dynamic.literal(inline = inline, volumeControl = volumeControl).asInstanceOf[VolumePanelOptions]
  }

}

@js.native
trait VolumePanelOptions extends ComponentOptions {
  val inline: UndefOr[Boolean]
  val volumeControl: UndefOr[VolumeControlOptions]
}

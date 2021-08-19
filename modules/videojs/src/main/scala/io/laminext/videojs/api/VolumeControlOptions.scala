package io.laminext.videojs.api

import io.laminext.videojs.api.components.VolumeBar
import scala.scalajs.js
import scala.scalajs.js.UndefOr

object VolumeControlOptions {

  def apply(
    volumeBar: UndefOr[VolumeBar] = js.undefined,
    vertical: UndefOr[Boolean] = js.undefined
  ): VolumeControlOptions = {
    val obj = js.Object().asInstanceOf[js.Dynamic]
    volumeBar.foreach(obj.volumeBar = _)
    vertical.foreach(obj.vertical = _)
    obj.asInstanceOf[VolumeControlOptions]
  }

}

@js.native
trait VolumeControlOptions extends ComponentOptions {
  val volumeBar: UndefOr[VolumeBar]
  val vertical: UndefOr[Boolean]
}

package io.laminext.videojs.api.components

import io.laminext.videojs.api.Component

import scala.scalajs.js

@js.native
trait VolumeControl extends Component {
  val volumeBar: VolumeBar = js.native
}

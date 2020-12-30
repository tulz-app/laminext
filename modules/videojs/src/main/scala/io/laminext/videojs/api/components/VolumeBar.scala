package io.laminext.videojs.api.components

import io.laminext.videojs.api.Component

import scala.scalajs.js

@js.native
trait VolumeBar extends Component {
  val volumeLevel: Component  = js.native
  val volumeHandle: Component = js.native
}

package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component

import scala.scalajs.js

@js.native
trait VolumeBar extends Component {
  val volumeLevel: Component  = js.native
  val volumeHandle: Component = js.native
}

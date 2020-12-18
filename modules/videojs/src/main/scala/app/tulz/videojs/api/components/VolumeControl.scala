package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component

import scala.scalajs.js

@js.native
trait VolumeControl extends Component {
  val volumeBar: VolumeBar = js.native
}

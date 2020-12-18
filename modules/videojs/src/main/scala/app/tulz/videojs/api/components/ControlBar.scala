package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component

import scala.scalajs.js

@js.native
trait ControlBar extends Component {
  val playToggle: Component            = js.native
  val fullscreenToggle: Component      = js.native
  val currentTimeDisplay: Component    = js.native
  val timeDivider: Component           = js.native
  val durationDisplay: Component       = js.native
  val remainingTimeDisplay: Component  = js.native
  val progressControl: ProgressControl = js.native
  val volumeControl: VolumeControl     = js.native
  val muteToggle: Component            = js.native
}

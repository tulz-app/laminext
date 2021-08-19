package io.laminext.videojs.api.components

import io.laminext.videojs.api.Component
import scala.scalajs.js

@js.native
trait ProgressControl extends Component {
  val seekBar: SeekBar = js.native
}

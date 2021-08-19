package io.laminext.videojs.api.components

import io.laminext.videojs.api.Component
import scala.scalajs.js

@js.native
trait SeekBar extends Component {
  val loadProgressBar: Component = js.native
  val playProgressBar: Component = js.native
  val seekHandle: Component      = js.native
}

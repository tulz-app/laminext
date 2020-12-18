package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component

import scala.scalajs.js

@js.native
trait ProgressControl extends Component {
  val seekBar: SeekBar = js.native
}

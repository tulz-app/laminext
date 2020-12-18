package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component

import scala.scalajs.js

@js.native
trait SeekBar extends Component {
  val loadProgressBar: Component = js.native
  val playProgressBar: Component = js.native
  val seekHandle: Component      = js.native
}

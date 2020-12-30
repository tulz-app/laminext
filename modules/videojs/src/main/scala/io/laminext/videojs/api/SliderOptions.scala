package io.laminext.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr

@js.native
trait SliderOptions extends ComponentOptions {
  val barName: UndefOr[String]
  val vertical: UndefOr[Boolean]
}

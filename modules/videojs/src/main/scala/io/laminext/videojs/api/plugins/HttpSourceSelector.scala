package io.laminext.videojs.api.plugins

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("videojs-http-source-selector", JSImport.Default)
@js.native
object HttpSourceSelector extends js.Any {

  val VERSION: String = js.native

}

@js.native
trait HttpSourceSelectorExt extends js.Any {

  def hlsQualitySelector(options: HlsQualitySelectorOptions): Unit
  def httpSourceSelector(): Unit

}

trait HlsQualitySelectorOptions extends js.Object {

  val displayCurrentQuality: Boolean

}

object HlsQualitySelectorOptions {

  def apply(
    displayCurrentQuality: Boolean
  ): HlsQualitySelectorOptions =
    js.Dynamic
      .literal(
        displayCurrentQuality = displayCurrentQuality
      ).asInstanceOf[HlsQualitySelectorOptions]

}

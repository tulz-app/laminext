package io.laminext.videojs.api

import scala.scalajs.js

object VideoSource {

  def apply(src: String, `type`: String): VideoSource = {
    js.Dynamic.literal(`type` = `type`, src = src).asInstanceOf[VideoSource]
  }

}

@js.native
trait VideoSource extends js.Object {

  val `type`: String = js.native
  val src: String    = js.native

}

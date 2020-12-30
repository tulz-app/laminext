package io.laminext.videojs.api

import scala.scalajs.js

@js.native
trait ChildDefinition extends js.Object {
  val name: String
  val children: js.UndefOr[js.Array[Child]]
}

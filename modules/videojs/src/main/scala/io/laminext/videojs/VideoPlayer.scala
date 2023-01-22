package io.laminext.videojs

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.base.ComponentBase
import io.laminext.videojs.api.Player
import org.scalajs.dom.HTMLVideoElement

class VideoPlayer(
  val el: ReactiveHtmlElement[HTMLVideoElement],
  val player: Signal[Option[Player]],
  val loadStart: EventStream[Player],
  val ended: EventStream[Player],
  val timeUpdate: EventStream[Player],
  val seek: EventStream[Player]
) extends ComponentBase[HTMLVideoElement]

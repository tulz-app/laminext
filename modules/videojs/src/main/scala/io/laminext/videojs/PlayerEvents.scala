package io.laminext.videojs

import io.laminext.videojs.api.Player
import com.raquo.laminar.api.L._

class PlayerEvents(
  val player: Signal[Option[Player]],
  val loadStart: EventStream[Player],
  val ended: EventStream[Player],
  val timeUpdate: EventStream[Player],
  val seek: EventStream[Player]
)

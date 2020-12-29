package app.tulz.videojs

import app.tulz.videojs.api.Player
import com.raquo.laminar.api.L._

class PlayerEvents(
  val ready: Signal[Option[Player]],
  val loadStart: EventStream[Player],
  val ended: EventStream[Player],
  val timeUpdate: EventStream[Player],
  val seek: EventStream[Player]
)

package app.tulz.videojs

import app.tulz.videojs.api.Player
import com.raquo.laminar.api.L._

class PlayerEvents(
  val ready: Signal[Boolean],
  val loadStart: EventStream[Player],
  val ended: EventStream[Unit],
  val timeUpdate: EventStream[Double]
)

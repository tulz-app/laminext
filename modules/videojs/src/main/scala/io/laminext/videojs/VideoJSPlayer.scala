package io.laminext.videojs

import io.laminext.videojs.api._
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import scala.scalajs.js

object VideoJSPlayer {

  lazy val h264Supported: String =
    videoTag().ref.canPlayType("""video/mp4; codecs="avc1"""")

  lazy val h265Supported: String =
    videoTag().ref.canPlayType("""video/mp4; codecs="hvc1"""")

  private def playerBus() = new EventBus[Player]

  def apply(
    options: VideoJSOptions,
  ): VideoPlayer = {
    var playerAPI: Option[Player] = None
    val readyPlayerVar            = Var(Option.empty[Player])
    val loadStartBus              = playerBus()
    val playBus                   = playerBus()
    val pauseBus                  = playerBus()
    val endedBus                  = playerBus()
    val timeUpdatedBus            = playerBus()
    val seekedBus                 = playerBus()

    val onReadyHandler = (player: Player) => {
      readyPlayerVar.writer.onNext(Some(player))
    }

    val onPlayHandler: js.Function = () => {
      playerAPI.foreach(playBus.writer.onNext)
    }

    val onLoadStartHandler: js.Function = () => {
      playerAPI.foreach(loadStartBus.writer.onNext)
    }

    val onPauseHandler: js.Function = () => {
      playerAPI.foreach(pauseBus.writer.onNext)
    }

    val onEndedHandler: js.Function = () => {
      playerAPI.foreach(endedBus.writer.onNext)
    }

    val onSeekedHandler: js.Function = () => {
      playerAPI.foreach(seekedBus.writer.onNext)
    }

    val onTimeUpdateHandler: js.Function = () => {
      playerAPI.foreach(timeUpdatedBus.writer.onNext)
    }

    val wrapper =
      videoTag(
        cls := "video-js",
        onUnmountCallback { _ =>
          playerAPI.foreach(_.dispose())
        },
        onMountBind { ctx =>
          val player = VideoJS(ctx.thisNode.ref, options, VjsUtils.ready(onReadyHandler))
          playerAPI = Some(player)

          player.on("play", onPlayHandler)
          player.on("pause", onPauseHandler)
          player.on("ended", onEndedHandler)
          player.on("seeked", onSeekedHandler)
          player.on("timeupdate", onTimeUpdateHandler)
          player.on("loadstart", onLoadStartHandler)

          Binder(
            ReactiveElement.bindSubscriptionUnsafe(_) { ctx =>
              new Subscription(
                ctx.owner,
                cleanup = () => {
                  player.off("play", onPlayHandler)
                  player.off("pause", onPauseHandler)
                  player.off("ended", onEndedHandler)
                  player.off("seeked", onSeekedHandler)
                  player.off("timeupdate", onTimeUpdateHandler)
                }
              )
            }
          )
        }
      )

    new VideoPlayer(
      el = wrapper,
      player = readyPlayerVar.signal,
      loadStart = loadStartBus.events,
      ended = endedBus.events,
      timeUpdate = timeUpdatedBus.events.throttle(1000),
      seek = seekedBus.events
    )
  }
}

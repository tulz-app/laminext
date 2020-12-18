package app.tulz.videojs.api

import app.tulz.videojs.PlayerEvents
import com.raquo.airstream.eventbus.EventBus
import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

import scala.scalajs.js
import scala.util.control.NonFatal

object VideoJSPlayer {

  lazy val h264Supported: String =
    video().ref.canPlayType("""video/mp4; codecs="avc1"""")

  lazy val h265Supported: String =
    video().ref.canPlayType("""video/mp4; codecs="hvc1"""")

  dom.console.log("VideoJS.VERSION", VideoJS.VERSION)
  dom.console.log("QualityLevels.VERSION", plugins.QualityLevels.VERSION)
  dom.console.log("HttpSourceSelector.VERSION", plugins.HttpSourceSelector.VERSION)

  def apply(
    options: VideoJSOptions,
    mods: Modifier[ReactiveHtmlElement.Base]*
  ): (ReactiveHtmlElement.Base, PlayerEvents, Signal[Option[Player]]) = {
    val playerVar      = Var(Option.empty[Player])
    var player: Player = null

    val readyVar       = Var(false)
    val loadStartBus   = new EventBus[Player]
    val playBus        = new EventBus[Unit]()
    val pauseBus       = new EventBus[Unit]()
    val endedBus       = new EventBus[Unit]()
    val timeUpdatedBus = new EventBus[Double]()
    val seekedBus      = new EventBus[Double]()

    val onReadyHandler = (player: Player) => {
      try {
        player.muted(true)
        readyVar.writer.onNext(true)
      } catch {
        case NonFatal(e) =>
          dom.console.log("cannot set muted on player")
      }
    }

    val onPlayHandler: js.Function = () => {
      playBus.writer.onNext((): Unit)
    }

    val onLoadStartHandler: js.Function = () => {
      loadStartBus.writer.onNext(player)
    }

    val onPauseHandler: js.Function = () => {
      pauseBus.writer.onNext((): Unit)
    }

    val onEndedHandler: js.Function = () => {
      endedBus.writer.onNext((): Unit)
    }

    val onSeekedHandler: js.Function = () => {
      if (player != null) {
        seekedBus.writer.onNext(player.currentTime())
      }
    }

    val onTimeUpdateHandler: js.Function = () => {
      if (player != null) {
        timeUpdatedBus.writer.onNext(player.currentTime())
      }
    }

    val events = new PlayerEvents(
      ready = readyVar.signal,
      loadStart = loadStartBus.events,
      ended = endedBus.events,
      timeUpdate = EventStream.merge(
        timeUpdatedBus.events.throttle(1000),
        seekedBus.events
      )
    )

    val wrapper =
      video(
        cls := "video-js hidden-if-no-js",
        mods,
        onUnmountCallback { _ =>
          player.dispose()
        },
//        if (dom.window.navigator.userAgent != "saa/ssr") {
        onMountBind { ctx =>
          try {
            player = VideoJS(ctx.thisNode.ref, options, VjsUtils.ready(onReadyHandler))
            //            player.asInstanceOf[plugins.HttpSourceSelectorExt].httpSourceSelector()

            player.on("play", onPlayHandler)
            player.on("pause", onPauseHandler)
            player.on("ended", onEndedHandler)
            player.on("seeked", onSeekedHandler)
            player.on("timeupdate", onTimeUpdateHandler)
            player.on("loadstart", onLoadStartHandler)

            playerVar.writer.onNext(Option(player))

            Binder(
              ReactiveElement.bindSubscription(_) { ctx =>
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
          } catch {
            case NonFatal(e) =>
              dom.console.error(e)
              throw e
          }
        }
//        } else {
//          emptyMod
//        }
      )

    (wrapper, events, playerVar.signal)
  }
}

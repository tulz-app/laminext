package io.laminext.videojs.api

import io.laminext.videojs.api

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.|
import scala.scalajs.js.JSConverters._

object VideoJSOptions {
  def apply(
    sources: Seq[VideoSource],
    controls: Boolean = true,
    autoplay: Boolean = false,
    loop: Boolean = false,
    fluid: Boolean = false,
    preload: String = "auto",
    poster: UndefOr[String] = js.undefined,
    width: UndefOr[Int] = js.undefined,
    height: UndefOr[Int] = js.undefined,
    techOrder: Seq[String] = Nil,
    aspectRatio: UndefOr[String] = js.undefined,
    bigPlayButton: UndefOr[Boolean] = js.undefined,
    controlBar: UndefOr[ControlBarOptions] = js.undefined,
    defaultVolume: UndefOr[Double] = js.undefined,
    html5: UndefOr[js.Any] = js.undefined,
    liveui: UndefOr[Boolean] = js.undefined,
    muted: UndefOr[Boolean] = js.undefined,
    nativeControlsForTouch: UndefOr[Boolean] = js.undefined,
    notSupportedMessage: UndefOr[String] = js.undefined,
    playbackRates: Seq[Double] = Nil,
    additional: Seq[(String, js.Any)] = Nil
  ): VideoJSOptions = {
    val obj = js.Object().asInstanceOf[js.Dynamic]
    obj.controls = controls
    obj.autoplay = autoplay
    obj.fluid = fluid
    obj.loop = loop
    obj.preload = preload
    obj.html5 = js.Dynamic.literal(
      vhs = js.Object()
    )
    if (poster.nonEmpty) obj.poster = poster
    if (width.nonEmpty) obj.width = width
    if (height.nonEmpty) obj.height = height
    if (sources.nonEmpty) obj.sources = sources.toJSArray
    if (techOrder.nonEmpty) obj.techOrder = techOrder.toJSArray
    if (aspectRatio.nonEmpty) obj.aspectRatio = playbackRates
    if (controlBar.nonEmpty) obj.controlBar = controlBar
    if (defaultVolume.nonEmpty) obj.defaultVolume = defaultVolume
    if (html5.nonEmpty) obj.html5 = html5
    if (liveui.nonEmpty) obj.liveui = liveui
    if (muted.nonEmpty) obj.html5 = muted
    if (nativeControlsForTouch.nonEmpty) obj.html5 = nativeControlsForTouch
    if (notSupportedMessage.nonEmpty) obj.html5 = notSupportedMessage
    if (playbackRates.nonEmpty) obj.playbackRates = playbackRates.toJSArray
    additional.foreach { case (key, value) => obj.updateDynamic(key)(value) }
    obj.asInstanceOf[VideoJSOptions]
  }
}

@js.native
trait VideoJSOptions extends ComponentOptions {

  /**
   * The controls option sets whether or not the player has controls that the user can interact with. Without controls the only way to start the video playing is with the autoplay attribute or through the API.
   */
  val controls: Boolean

  /**
   * If autoplay is true, the video will start playing as soon as page is loaded (without any interaction from the user). NOT SUPPORTED BY APPLE iOS DEVICES. Apple blocks the autoplay functionality in an effort to protect it's customers from unwillingly using a lot of their (often expensive) monthly data plans. A user touch/click is required to start the video in this case.
   */
  val autoplay: Boolean

  /**
   * The loop attribute causes the video to start over as soon as it ends. This could be used for a visual effect like clouds in the background.
   */
  val loop: Boolean

  /**
   * The preload attribute informs the browser whether or not the video data should begin downloading as soon as the video tag is loaded. The options are auto, metadata, and none.
   * 'auto': Start loading the video immediately (if the browser agrees). Some mobile devices like iPhones and iPads will not preload the video in order to protect their users' bandwidth. This is why the value is called 'auto' and not something more final like 'true'.
   * 'metadata': Load only the meta data of the video, which includes information like the duration and dimensions of the video.
   * 'none': Don't preload any of the video data. This will wait until the user clicks play to begin downloading.
   */
  val preload: String

  /**
   * The poster attribute sets the image that displays before the video begins playing. This is often a frame of the video or a custom title screen. As soon as the user clicks play the image will go away.
   */
  val poster: UndefOr[String]

  /**
   * The width attribute sets the display width of the video.
   */
  val width: UndefOr[Int]

  /**
   * The height attribute sets the display height of the video.
   */
  val height: UndefOr[Int]

  val fluid: Boolean

  val techOrder: js.Array[String]

  val sources: js.Array[VideoSource]

  val aspectRatio: String

  val bigPlayButton: UndefOr[Boolean]

  val controlBar: UndefOr[api.ControlBarOptions | Boolean]

  //    val textTrackSettings: UndefOr[videojs.TextTrackSettingsOptions]
  val defaultVolume: Double

  val html5: js.Any

  /**
   * Video.js indicates that the user is interacting with the player by way of the "vjs-user-active" and "vjs-user-inactive" classes and the "useractive" event.
   *
   * The inactivityTimeout determines how many milliseconds of inactivity is required before declaring the user inactive. A value of 0 indicates that there is no inactivityTimeout and the user will never be considered inactive.
   */
  val inactivityTimeout: Double

  val language: String
  //    val languages?: { [code: string]: videojs.LanguageTranslations };

  val liveui: Boolean

  val muted: Boolean

  val nativeControlsForTouch: Boolean

  val notSupportedMessage: String

  val playbackRates: js.Array[Double]
  //    val plugins?: Partial<VideoJsPlayerPluginOptions>;
  //    val sourceOrder: Boolean
  //    val src: String
  //    val tracks: js.Array[ videojs.TextTrackOptions]

}

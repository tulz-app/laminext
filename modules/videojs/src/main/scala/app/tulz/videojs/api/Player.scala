package app.tulz.videojs.api

import app.tulz.videojs.api.components.ControlBar
import org.scalajs.dom.raw.TimeRanges
import org.scalajs.dom.Element
import org.scalajs.dom.MediaError

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

//@js.native
//@JSName("Player")
//object Player extends js.Object {
//  /**
//    * Player object constructor
//    * @param tag The original video tag used for configuring options
//    * @param options Object of option names and values
//    * @param ready Ready callback function
//    */
//  def apply(tag: Element, options: js.Object = ???, ready: js.Function = ???): Player = js.native
//
//  /**
//    * Gets tag settings
//    * @param tag The player tag
//    * @return An array of sources and track objects
//    */
//  def getTagSettings(tag: Element): js.Object = js.native
//}

/**
 * @see [[http://docs.videojs.com/docs/api/player.html]]
 */
//noinspection AccessorLikeMethodIsEmptyParen
@js.native
trait Player extends Component {

  /**
   * Add a remote text track
   * @param options Options for remote text track
   */
  def addRemoteTextTrack(options: scalajs.js.Object): Unit = scalajs.js.native

  /**
   * Add a text track In addition to the W3C settings we allow adding additional info through options. http://www.w3.org/html/wg/drafts/html/master/embedded-content-0.html#dom-media-addtexttrack
   * @param kind Captions, subtitles, chapters, descriptions, or metadata
   * @param label Optional label
   * @param language Optional language
   */
  def addTextTrack(kind: String, label: String = ???, language: String = ???): Unit = scalajs.js.native

  /**
   * Get/Set the aspect ratio
   * @param ratio Aspect ratio for player
   */
  def aspectRatio(ratio: String = ???): String = scalajs.js.native

  /**
   * Get or set the autoplay attribute.
   * @param value Boolean to determine if video should autoplay
   */
  def autoplay(value: Boolean = ???): Boolean = scalajs.js.native

  /**
   * Get a TimeRange object with the times of the video that have been downloaded
   * If you just want the percent of the video that's been downloaded, use bufferedPercent.
   * @return TimeRange object
   */
  def buffered(): TimeRanges = js.native

  /**
   * Get the percent (as a decimal) of the video that's been downloaded.
   * 0 means none, 1 means all. (This method isn't in the HTML5 spec, but it's very convenient)
   * @return The end of the last buffered time range
   * @example {{{
   *   var howMuchIsDownloaded = myPlayer.bufferedPercent();
   * }}}
   */
  def bufferedPercent(): Double = js.native

  /**
   * Check whether the player can play a given mimetype
   * @param `type` The mimetype to check
   */
  def canPlayType(`type`: String): Boolean = js.native

  /**
   * Get or set whether or not the controls are showing.
   * @param bool Set controls to showing or not
   * @return Controls are showing
   */
  def controls(bool: Boolean = ???): Boolean = js.native

  /**
   * Create the component's DOM element
   */
  def createEl(): Element = js.native

  /**
   * Returns the fully qualified URL of the current source value e.g. `http://mysite.com/video.mp4`
   * Can be used in conjuction with currentType to assist in rebuilding the current source object.
   */
  def currentSrc(): String = js.native

  /**
   * Get the current time (in seconds)
   * @return The time in seconds, when not setting
   * @example {{{
   *   var whereYouAt = myPlayer.currentTime();
   * }}}
   */
  def currentTime(): Double = js.native

  /**
   * Set the current time (in seconds)
   * @param seconds The time to seek to
   * @return Self, when the current time is set
   * @example {{{
   *   myPlayer.currentTime(120); // 2 minutes into the video
   * }}}
   */
  def currentTime(seconds: Int | String = ???): this.type = js.native

  /**
   * Get the current source type e.g. video/mp4.
   * This can allow you rebuild the current source object so that you could load the same source and tech later.
   * @return
   */
  def currentType(): String = js.native

  /**
   * Get/set dimension for player
   * @param dimension Either width or height
   * @param value Value for dimension
   * @return Height when getting
   */
  def dimension(dimension: String, value: Int = ???): Int = js.native

  /**
   * Get the length in time of the video in seconds
   * @param seconds Duration when setting
   * @return The duration of the video in seconds when getting
   * @note The video must have started loading before the duration can be known, and in the case of Flash, may not be known until the video starts playing.
   */
  def duration(seconds: Int = ???): Int = js.native

  /**
   * Returns whether or not the player is in the "ended" state.
   * @return True if the player is in the ended state, false if not.
   */
  def ended(): Boolean = js.native

  /**
   * When fullscreen isn't supported we can stretch the video container to as wide as the browser will let us.
   */
  def enterFullWindow(): Unit = js.native

  /**
   * Set the current MediaError
   * @param err A MediaError or a String/Number to be turned into a MediaError
   * @return Player
   */
  def error(err: MediaError | String | Int): this.type = js.native

  /**
   * Get the current MediaError
   * @return MediaError or `null`
   */
  def error(): MediaError = js.native

  /**
   * Return the video to its normal size after having been in full screen mode
   */
  def exitFullscreen(): Unit = js.native

  /**
   * Exit full window
   */
  def exitFullWindow(): Unit = js.native

  /**
   * Add/remove the vjs-fluid class
   * @param bool Value of true adds the class, value of false removes the class
   */
  def fluid(bool: Boolean): Unit = js.native

  /**
   * Check for call to either exit full window or full screen on ESC key
   * @param event Event to check for key press
   */
  def fullWindowOnEscKey(event: String): Unit = js.native

  /**
   * Get object for cached values.
   */
  def getCache(): js.Object = js.native

  /**
   * Check if the player is in fullscreen mode
   * @note As of the latest HTML5 spec, isFullscreen is no longer an official property and instead document.fullscreenElement is used. But isFullscreen is still a valuable property for internal player workings.
   */
  def isFullscreen(): Boolean = js.native

  /**
   * Tell the player it's in fullscreen
   * @param isFS Update the player's fullscreen state
   * @return Self
   */
  def isFullscreen(isFS: Boolean): this.type = js.native

  /**
   * The player's language code
   * @return The locale string when getting
   */
  def language(): String = js.native

  /**
   * The player's language code
   * @param code The locale string
   * @return Self when setting
   * @note The language should be set in the player options if you want the
   * the controls to be built with a specific language. Changing the lanugage
   * later will not update controls text.
   */
  def language(code: String): this.type = js.native

  /**
   * Get the player's language dictionary
   * Merge every time, because a newly added plugin might call videojs.addLanguage() at any time
   * Languages specified directly in the player options have precedence
   * @return Array of languages
   */
  def languages(): js.Array[String] = js.native

  /**
   * Begin loading the src data.
   * @return Returns the player
   */
  def load(): this.type = js.native

  /**
   * Get the loop attribute on the video element.
   * @return The loop attribute value when getting
   */
  def loop(): Boolean = js.native

  /**
   * Set the loop attribute on the video element.
   * @param value Boolean to determine if video should loop
   * @return Returns the player when setting
   */
  def loop(value: Boolean): this.type = js.native

  /**
   * Get the current muted state
   */
  def muted(): Boolean = js.native

  /**
   * Turn mute on or off
   * @param value True to mute, false to unmute
   * @return Returns the player when setting
   */
  def muted(value: Boolean): this.type = js.native

  /**
   * Returns the current state of network activity for the element, from the codes in the list below.
   * <ul>
   *  <li>NETWORK_EMPTY (numeric value 0) The element has not yet been initialised. All attributes are in their initial states.</li>
   *  <li>NETWORK_IDLE (numeric value 1) The element's resource selection algorithm is active and has selected a resource, but it is not actually using the network at this time.</li>
   *  <li>NETWORK_LOADING (numeric value 2) The user agent is actively trying to download data.</li>
   *  <li>NETWORK_NO_SOURCE (numeric value 3) The element's resource selection algorithm is active, but it has not yet found a resource to use.</li>
   * </ul>
   */
  def networkState(): Int = js.native

  /**
   * Pause the video playback
   */
  def pause(): this.type = js.native

  /**
   * Check if the player is paused
   */
  def paused(): Boolean = js.native

  /**
   * Start media playback
   */
  def play(): this.type = js.native

  /**
   * Sets the current playback rate. A playback rate of 1.0 represents normal speed and 0.5 would indicate half-speed playback, for instance.
   * @param rate New playback rate to set.
   */
  def playbackRate(rate: Double): this.type = js.native

  /**
   * Gets the current playback rate. A playback rate of 1.0 represents normal speed and 0.5 would indicate half-speed playback, for instance.
   */
  def playbackRate(): Double = js.native

  /**
   * Set the poster image source url
   * @param src Poster image source URL
   */
  def poster(src: String): this.type = js.native

  /**
   * Get the poster image source url
   * @return Poster image source URL
   */
  def poster(): String = js.native

  /**
   * Set the preload attribute
   * @param value Boolean to determine if preload should be used
   */
  def preload(value: Boolean): this.type = js.native

  /**
   * Get the preload attribute
   */
  def preload(): Boolean = js.native

  /**
   * Returns a value that expresses the current state of the element with respect to rendering the current playback position, from the codes in the list below.
   * <ul>
   *   <li>HAVE_NOTHING (numeric value 0) No information regarding the media resource is available.</li>
   *   <li>HAVE_METADATA (numeric value 1) Enough of the resource has been obtained that the duration of the resource is available.</li>
   *   <li>HAVE_CURRENT_DATA (numeric value 2) Data for the immediate current playback position is available.</li>
   *   <li>HAVE_FUTURE_DATA (numeric value 3) Data for the immediate current playback position is available, as well as enough data for the user agent to advance the current playback position in the direction of playback.</li>
   *   <li>HAVE_ENOUGH_DATA (numeric value 4) The user agent estimates that enough data is available for playback to proceed uninterrupted.</li>
   * </ul>
   */
  def readyState(): Int = js.native

  /**
   * Calculates how much time is left.
   */
  def remainingTime(): Int = js.native

  /**
   * Get an array of remote html track elements
   */
  def remoteTextTrackEls(): js.Array[Element] = js.native

  /**
   * Get an array of remote text tracks
   * @return
   */
  def remoteTextTracks(): js.Array[String] = js.native

  /**
   * Remove a remote text track
   * @param track Remote text track to remove
   */
  def removeRemoteTextTrack(track: js.Object): Unit = js.native

  /**
   * Report user activity
   * @param event Event object
   */
  def reportUserActivity(event: js.Object): Unit = js.native

  /**
   * Increase the size of the video to full screen
   * In some browsers, full screen is not supported natively, so it enters "full window mode", where the video fills the browser window.
   * In browsers and devices that support native full screen, sometimes the browser's default controls will be shown, and not the Video.js custom skin.
   * This includes most mobile devices (iOS, Android) and older versions of Safari.
   */
  def requestFullscreen(): Unit = js.native

  /**
   * Reset the player. Loads the first tech in the techOrder, and calls `reset` on the `tech`.
   */
  def reset(): this.type = js.native

  /**
   * @param isScrubbing True/false the user is scrubbing
   */
  def scrubbing(isScrubbing: Boolean): this.type = js.native

  /**
   * Returns whether or not the user is "scrubbing".
   * Scrubbing is when the user has clicked the progress bar handle and is dragging it along the progress bar.
   */
  def scrubbing(): Boolean = js.native

  /**
   * Returns the TimeRanges of the media that are currently available for seeking to.
   */
  def seekable(): TimeRanges = js.native

  /**
   * Returns whether or not the player is in the "seeking" state.
   */
  def seeking(): Boolean = js.native

  /**
   * Select source based on tech-order or source-order
   * Uses source-order selection if `options.sourceOrder` is truthy. Otherwise,
   * defaults to tech-order selection
   * @param sources The sources for a media asset
   * @return Object of source and tech order, otherwise false
   */
  def selectSource(sources: js.Array[VideoSource]): js.Object | Boolean = js.native

  /**
   * The source function updates the video source.
   * There are three types of variables you can pass as the argument.
   * <ul>
   * <li><b>URL String</b>: A URL to the the video file. Use this method if you are sure the current playback technology (HTML5/Flash) can support the source you provide. Currently only MP4 files can be used in both HTML5 and Flash.</li>
   * <li><b>Source Object (or element)</b>: A javascript object containing information about the source file. Use this method if you want the player to determine if it can support the file using the type information.</li>
   * <li><b>Array of Source Objects</b>: To provide multiple versions of the source so that it can be played using HTML5 across browsers you can use an array of source objects. Video.js will detect which version is supported and load that file.</li>
   * </ul>
   * @param source The source URL, object, or array of sources
   * @return The player when setting
   */
  def src(source: String | js.Array[VideoSource] | VideoSource): this.type = js.native

  /**
   * @return The current video source when getting
   */
  def src(): String = js.native

  /**
   * Check to see if fullscreen is supported
   */
  def supportsFullScreen(): Boolean = js.native

  /**
   * Return a reference to the current tech. It will only return a reference to the tech if given an object with the IWillNotUseThisInPlugins property on it. This is try and prevent misuse of techs by plugins.
   * @return The Tech
   */
  def tech(safety: js.Object): js.Object = js.native

  /**
   * Get an array of associated text tracks. captions, subtitles, chapters, descriptions
   * @see [[http://www.w3.org/html/wg/drafts/html/master/embedded-content-0.html#dom-media-texttracks]]
   * @return Array of track objects
   */
  def textTracks(): js.Array[js.Object] = js.native

  /**
   * Converts track info to JSON
   * @return JSON object of options
   */
  def toJSON(): js.Object = js.native

  /**
   * Update styles of the player element (height, width and aspect ratio)
   */
  def updateStyleEl_(): Unit = js.native

  /**
   * Set if user is active
   * @param bool Value when setting
   */
  def userActive(bool: Boolean): this.type = js.native

  /**
   * Get if user is active
   * @return Value if user is active user when getting
   */
  def userActive(): Boolean = js.native

  /**
   * Get video height
   */
  def videoHeight(): Int = js.native

  /**
   * Get video width
   */
  def videoWidth(): Int = js.native

  /**
   * Set the current volume of the media
   * 0 is off (muted), 1.0 is all the way up, 0.5 is half way.
   * @param percentAsDecimal The new volume as a decimal percent
   */
  def volume(percentAsDecimal: Double): this.type = js.native

  /**
   * Get the current volume of the media
   * 0 is off (muted), 1.0 is all the way up, 0.5 is half way.
   */
  def volume(): Double = js.native

  // Components
  val posterImage: Component      = js.native
  val textTrackDisplay: Component = js.native
  val loadingSpinner: Component   = js.native
  val bigPlayButton: Component    = js.native
  val controlBar: ControlBar      = js.native
}

object IWillNotUseThisInPlugins extends js.Object {

  val IWillNotUseThisInPlugins = true

}

@js.native
trait HlsTechHxrBeforeRequestOptions extends js.Object {

  var uri: String                    = js.native
  var headers: js.UndefOr[js.Object] = js.native

}

@js.native
trait HlsTechHxr extends js.Object {

  var beforeRequest: js.Function1[HlsTechHxrBeforeRequestOptions, Unit] = js.native

}

@js.native
trait HlsTech extends js.Object {

  val xhr: HlsTechHxr

}

@js.native
trait TechWithHls extends js.Object {

  val hls: HlsTech = js.native

}

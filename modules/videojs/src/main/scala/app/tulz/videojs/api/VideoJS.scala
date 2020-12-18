package app.tulz.videojs.api

import org.scalajs.dom.Element
import org.scalajs.dom.raw.TimeRanges

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

//noinspection AccessorLikeMethodIsEmptyParen
// TODO: Documentation
@js.native
@JSImport("video.js", JSImport.Default)
object VideoJS extends Component {

  val VERSION: String = js.native

  /**
   * Doubles as the main function for users to create a player instance and also the main library object.
   * The videojs function can be used to initialize or retrieve a player.
   * @param element Video element or video element ID
   * @param settings Optional options object for config/settings
   * @param ready Optional ready callback
   */
  def apply(
    element: String | Element,
    settings: js.UndefOr[VideoJSOptions] = js.undefined,
    ready: js.UndefOr[js.Function] = js.undefined
  ): Player = js.native

  def addClass(element: Element, classToAdd: String): Unit = js.native

  /**
   * Adding languages so that they're available to all players.
   * @param code The language code or dictionary property
   * @param data The data values to be translated
   * @example {{{
   *   VideoJS.addLanguage("es", js.Dynamic.literal(Hello = "Hola"))
   * }}}
   */
  def addLanguage(code: String, data: js.Object): Unit = js.native

  def appendContent(el: Element, content: js.Any): Unit = js.native

  def createTimeRange(start: Int | js.Array[TimeRanges], end: Int): TimeRanges = js.native

  def emptyEl(element: Element): Unit = js.native

  def extend(cls: js.Object, fn: js.Object): js.Object = js.native

  def formatTime(seconds: Int, guide: Int): String = js.native

  def getAttributes(tag: Element): js.Object = js.native

  /**
   * Get a component class object by name
   */
  def getComponent(name: String): js.Object = js.native

  /**
   * Get an object with the currently created players, keyed by player ID
   */
  def getPlayers(): js.Object = js.native

  def hasClass(element: Element, classToCheck: String): Boolean = js.native

  def insertContent(el: Element, content: js.Any): Unit = js.native

  /**
   * Returns whether the url passed is a cross domain request or not.
   * @param url The url to check
   */
  def isCrossOrigin(url: String): Boolean = js.native

  def isEl(value: js.Any): Boolean = js.native

  def isTextNode(value: js.Any): Boolean = js.native

  def mergeOptions(defaults: js.Object, overrides: js.Object, etc: js.Object): js.Object = js.native

  def off(component: Element | js.Object, event: String | js.Array[String], handler: js.Function): this.type = js.native

  def on(component: Element | js.Object, event: String | js.Array[String], handler: js.Function): this.type = js.native

  def one(component: Element | js.Object, event: String | js.Array[String], handler: js.Function): this.type = js.native

  def parseUrl(url: String): js.Object = js.native

  /**
   * Create a Video.js player plugin.
   * Plugins are only initialized when options for the plugin are included in the player options, or the plugin function on the player instance is called.
   * See the plugin guide in the docs for a more detailed example
   * @param name The plugin name
   * @param fn The plugin function that will be called with options
   */
  def plugin(name: String, fn: js.Function): Unit = js.native

  /**
   * Register a component so it can referred to by name.
   * Used when adding to other components, either through addChild component.addChild('myComponent') or through default children options { children: ['myComponent'] }.
   * @param name Class name of the component
   * @param comp Component class
   * @return The newly registered component
   * @example {{{
   *   // Get a component to subclass
   *   var VjsButton = videojs.getComponent('Button');
   *   // Subclass the component (see 'extend' doc for more info)
   *   var MySpecialButton = videojs.extend(VjsButton, {});
   *   // Register the new component
   *   VjsButton.registerComponent('MySpecialButton', MySpecialButton);
   *   // (optionally) add the new component as a default player child
   *  myPlayer.addChild('MySpecialButton');
   * }}}
   */
  def registerComponent(name: String, comp: js.Object): js.Object = js.native

  /**
   * Register a Tech so it can referred to by name. This is used in the tech order for the player.
   * @param name Class name of the tech
   * @param tech Tech class
   * @return The newly registered Tech
   * @example {{{
   *     // get the Html5 Tech
   *     var Html5 = videojs.getTech('Html5');
   *     var MyTech = videojs.extend(Html5, {});
   *     // Register the new Tech
   *     VjsButton.registerTech('Tech', MyTech);
   *     var player = videojs('myplayer', {
   *       techOrder: ['myTech', 'html5']
   *     });
   * }}}
   */
  def registerTech(name: String, tech: js.Object): js.Object = js.native

  def removeClass(element: Element, classToRemove: String): Unit = js.native

  def setAttributes(el: Element, attributes: js.Object = ???): Unit = js.native

  def toggleElClass(element: Element, classToToggle: String, predicate: Boolean | js.Function = ???): Unit = js.native

  @JSName("trigger")
  def triggerEl(element: js.Object, event: String | js.Object, hash: js.Object = ???): Unit = js.native
}

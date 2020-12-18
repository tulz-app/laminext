package app.tulz.videojs.api.components

import app.tulz.videojs.api.Component
import app.tulz.videojs.api.EventTarget
import app.tulz.videojs.api.SliderOptions
import org.scalajs.dom.Element

import scala.scalajs.js

@js.native
trait Slider extends Component {

  val options_ : SliderOptions

  /**
   * Enable controls for this slider if they are disabled
   */
  def enable(): Unit

  /**
   * Are controls are currently enabled for this slider or not.
   *
   * @return true if controls are enabled, false otherwise
   */
  def enabled(): Boolean

  /**
   * Disable controls for this slider if they are enabled
   */
  def disable(): Unit

  /**
   * Create the `Slider`s DOM element.
   *
   * @param type
   *        Type of element to create.
   *
   * @param [props={}]
   *        List of properties in Object form.
   *
   * @param [attributes={}]
   *        list of attributes in Object form.
   *
   * @return The element that gets created.
   */
  def createEl(`type`: String, props: js.Any, attributes: js.Any): Element

  /**
   * Handle `mousedown` or `touchstart` events on the `Slider`.
   *
   * @param event
   *        `mousedown` or `touchstart` event that triggered this function
   *
   * listens mousedown
   * listens touchstart
   * fires Slider#slideractive
   */
  def handleMouseDown(event: EventTarget.Event): Unit

  /**
   * Handle the `mousemove`, `touchmove`, and `mousedown` events on this `Slider`.
   * The `mousemove` and `touchmove` events will only only trigger this function during
   * `mousedown` and `touchstart`. This is due to {@link Slider#handleMouseDown} and
   * {@link Slider#handleMouseUp}.
   *
   * @param event
   *        `mousedown`, `mousemove`, `touchstart`, or `touchmove` event that triggered
   *        this function
   *
   * listens mousemove
   * listens touchmove
   */
  def handleMouseMove(event: EventTarget.Event): Unit

  /**
   * Handle `mouseup` or `touchend` events on the `Slider`.
   *
   * @param event
   *        The `mouseup` event that caused this to run.
   *
   * listens touchend
   * listens mouseup
   * fires Slider#sliderinactive
   */
  def handleMouseUp(event: EventTarget.Event): Unit

  /**
   * Update the progress bar of the `Slider`.
   *
   * @return The percentage of progress the progress bar represents as a
   *          number from 0 to 1.
   */
  def update(): Double

  /**
   * Calculate distance for slider
   *
   * @param event
   *        The event that caused this function to run.
   *
   * @return The current position of the Slider.
   *         - position.x for vertical `Slider`s
   *         - position.y for horizontal `Slider`s
   */
  def calculateDistance(event: EventTarget.Event): Double

  /**
   * Handle a `focus` event on this `Slider`.
   *
   * listens focus
   */
  def handleFocus(): Unit

  /**
   * Handle a `blur` event on this `Slider`.
   *
   * listens blur
   */
  def handleBlur(): Unit

  /**
   * Listener for click events on slider, used to prevent clicks
   *   from bubbling up to parent elements like button menus.
   *
   * @param event
   *        Event that caused this object to run
   */
  def handleClick(event: EventTarget.Event): Unit;

  /**
   * Get/set if slider is horizontal for vertical
   *
   * @param [bool]
   *        - true if slider is vertical,
   *        - false is horizontal
   *
   * @return - true if slider is vertical, and getting
   *         - false if the slider is horizontal, and getting
   */
  def vertical(bool: Boolean): Unit;

  def vertical(): Boolean;

}

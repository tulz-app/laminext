package io.laminext.videojs.api

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait EventTarget extends js.Object {
  def addEventListener(`type`: String | js.Array[String], fn: EventTarget.EventListener): Unit;
  def dispatchEvent(event: String | EventTarget.Event): Unit
  def off(`type`: String | js.Array[String], fn: EventTarget.EventListener): Unit
  def on(`type`: String | js.Array[String], fn: EventTarget.EventListener): Unit
  def one(`type`: String | js.Array[String], fn: EventTarget.EventListener): Unit
  def removeEventListener(`type`: String | js.Array[String], fn: EventTarget.EventListener): Unit
  def trigger(event: String | EventTarget.Event): Unit
}

object EventTarget {

  /**
   * A Custom DOM event.
   * @see
   *   [Properties]{@link https://developer.mozilla.org/en-US/docs/Web/API/CustomEvent}
   */
  @js.native
  trait Event extends js.Any

  type EventListener = (Event, js.Any) => Unit

}

package app.tulz.videojs

import scala.scalajs.js
import scala.scalajs.js.|

package object api {

  @js.native
  trait ChildDefinition extends js.Object {
    val name: String
    val children: js.UndefOr[js.Array[Child]]

  }

  type Child = String | ChildDefinition

  @js.native
  trait ComponentOptions extends js.Object {
    val children: js.UndefOr[js.Array[Child]]
  }

  object EventTarget {

    /**
     * A Custom DOM event.
     * @see [Properties]{@link https://developer.mozilla.org/en-US/docs/Web/API/CustomEvent}
     */
    @js.native
    trait Event extends js.Any

    type EventListener = (Event, js.Any) => Unit
  }

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

}

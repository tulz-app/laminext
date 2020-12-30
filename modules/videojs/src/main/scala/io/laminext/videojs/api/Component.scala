package io.laminext.videojs.api

import org.scalajs.dom.Element
import org.scalajs.dom.raw.NodeList

import scala.scalajs.js
import scala.scalajs.js.|

// TODO: Documentation
//@js.native
//@JSImport("Component")
//object Component extends js.Object {
//
//  /**
//    * Constructor
//    * @param player Main Player
//    * @param options Object of option names and values
//    * @param ready Ready callback function
//    */
//  def apply(player: Player, options: VideoJSOptions, ready: js.Function): Component = js.native
//
//  def getComponent(name: String): js.Object = js.native
//
//  def registerComponent(name: String, comp: js.Object): Unit = js.native
//
//}

/**
 * @see [[http://docs.videojs.com/docs/api/component.html]]
 */
@js.native
trait Component extends js.Object {
  def $(selector: String, context: Element | String = ???): Element = js.native

  def $$(selector: String, context: Element | String = ???): NodeList = js.native

  def addChild(child: String | Component, options: js.Object = ???): Unit = js.native

  def addClass(classToAdd: String): Unit = js.native

  def buildCSSClass(): String = js.native

  def children(): js.Array[Component] = js.native

  def clearInterval(intervalId: Int): Unit = js.native

  def clearTimeout(timeoutId: Int): Unit = js.native

  def contentEl(): Element = js.native

  def createEl(tagName: String = ???, properties: js.Object = ???, attributes: js.Object = ???): Element = js.native

  def dimensions(width: Int | String, height: Int | String): Unit = js.native

  def dispose(): Unit = js.native

  def el(): Element = js.native

  def enableTouchActivity(): Unit = js.native

  def getChild(name: String): Component = js.native

  def getChildById(id: String): Component = js.native

  def hasClass(classToCheck: String): Boolean = js.native

  def height(num: Int | String = ???, skipListeners: Boolean = ???): Int = js.native

  def hide(): Unit = js.native

  def id(): String = js.native

  def initChildren(): Unit = js.native

  def name(): String = js.native

  def off(component: Component, event: String, handler: js.Function): Unit = js.native

  def off(event: String, handler: js.Function): Unit = js.native

  def on(component: Component, event: String, handler: js.Function): Unit = js.native

  def on(event: String, handler: js.Function): Unit = js.native

  def one(component: Component, event: String, handler: js.Function): Unit = js.native

  def one(event: String, handler: js.Function): Unit = js.native

  def options(obj: js.Object): js.Object = js.native

  def player(): Player = js.native

  def ready(fn: js.Function, sync: Boolean): this.type = js.native

  def removeChild(component: Component): Unit = js.native

  def removeClass(classToRemove: String): Unit = js.native

  def setInterval(fn: js.Function, interval: Int): Unit = js.native

  def setTimeout(fn: js.Function, timeout: Int): Unit = js.native

  def show(): Unit = js.native

  def toggleClass(classToToggle: String, predicate: js.Function | Boolean = ???): Unit = js.native

  def trigger(event: js.Object | String, hash: js.Object = ???): Unit = js.native

  def triggerReady(): Unit = js.native

  def width(num: Int | String, skipListeners: Boolean): Int = js.native

  def handleKeyPress(): Unit = js.native

  def controlText(el: Element): Element = js.native

  def handleBlur(): Unit = js.native

  def handleClick(): Unit = js.native

  def handleFocus(): Unit = js.native
}

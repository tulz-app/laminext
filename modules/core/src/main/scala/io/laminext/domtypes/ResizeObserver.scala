package io.laminext.domtypes

import org.scalajs.dom.ClientRect
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.raw.SVGElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal
import scala.scalajs.js.|

@js.native
@JSGlobal
class ResizeObserver(
  callback: js.Function2[js.Array[ResizeObserverEntry], ResizeObserver, Unit]
) extends js.Object {

  def disconnect(): Unit                                                                                  = js.native
  def observe(target: HTMLElement | SVGElement, options: js.UndefOr[ObserveOptions] = js.undefined): Unit = js.native
  def unobserve(target: HTMLElement | SVGElement): Unit                                                   = js.native

}

@js.native
trait ObserverCallbackOptions extends js.Any {

  var box: String

}

@js.native
trait ObserveOptions extends js.Any {

  var box: String

}
@js.native
trait ResizeObserverEntry extends js.Any {

  val borderBoxSize: ResizeObserverBorderBoxSize
  val contentBoxSize: ResizeObserverContentBoxSize
  val contentRect: ClientRect
  val target: HTMLElement | SVGElement

}
@js.native
trait ResizeObserverBorderBoxSize extends js.Any {

  val blockSize: Any
  val inlineSize: Any

}

@js.native
trait ResizeObserverContentBoxSize extends js.Any {

  val blockSize: Any
  val inlineSize: Any

}

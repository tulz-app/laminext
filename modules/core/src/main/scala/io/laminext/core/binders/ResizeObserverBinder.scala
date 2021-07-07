package io.laminext.core
package binders

import com.raquo.airstream.ownership.DynamicSubscription
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.domext.ResizeObserver
import io.laminext.domext.ResizeObserverEntry
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.raw.SVGElement

import scala.scalajs.js.|

class ResizeObserverBinder[El <: ReactiveElement[dom.raw.HTMLElement]](
  onNext: ResizeObserverEntry => Unit
) extends BinderWithStartStop[El] {

  private var element: El                                 = _
  private var maybeResizeObserver: Option[ResizeObserver] = Option.empty

  def doStop(): Unit = {
    maybeResizeObserver.foreach { resizeObserver =>
      resizeObserver.disconnect()
    }
  }

  def doStart(): Unit = {
    maybeResizeObserver.foreach { resizeObserver =>
      resizeObserver.observe(element.ref)
    }
  }

  override def bind(element: El): DynamicSubscription = {
    if (maybeResizeObserver.isDefined) {
      dom.console.error("resizeObserver can not be re-used")
    }
    this.element = element
    maybeResizeObserver = Some(
      new ResizeObserver(
        callback = (entries, _) => {
          if (entries.nonEmpty && entries.head.target == element.ref.asInstanceOf[HTMLElement | SVGElement]) {
            onNext(entries.head)
          }
        }
      )
    )
    super.bind(element)
  }

}

package io.laminext.core
package binders

import com.raquo.airstream.ownership.DynamicSubscription
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import org.scalajs.dom.ResizeObserver
import org.scalajs.dom.ResizeObserverEntry
import org.scalajs.dom.ResizeObserverOptions

import scala.scalajs.js

class ResizeObserverBinder[El <: ReactiveElement[dom.Element]](
  onNext: ResizeObserverEntry => Unit,
  options: js.UndefOr[ResizeObserverOptions] = js.undefined
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
      options.fold(resizeObserver.observe(element.ref))(resizeObserver.observe(element.ref, _))
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
          if (entries.nonEmpty && entries.head.target == element.ref) {
            onNext(entries.head)
          }
        }
      )
    )
    super.bind(element)
  }

}

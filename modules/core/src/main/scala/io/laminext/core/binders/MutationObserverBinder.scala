package io.laminext.core.binders

import com.raquo.airstream.ownership.DynamicSubscription
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.domext.ResizeObserver
import io.laminext.domext.ResizeObserverEntry
import org.scalajs.dom
import org.scalajs.dom.raw.HTMLElement
import org.scalajs.dom.raw.MutationObserver
import org.scalajs.dom.raw.MutationObserverInit
import org.scalajs.dom.raw.MutationRecord
import org.scalajs.dom.raw.SVGElement

import scala.scalajs.js
import scala.scalajs.js.|

class MutationObserverBinder[El <: ReactiveElement[dom.raw.HTMLElement]](
  onNext: Seq[MutationRecord] => Unit,
  options: MutationObserverInit
) extends BinderWithStartStop[El] {

  private var element: El                                     = _
  private var maybeMutationObserver: Option[MutationObserver] = Option.empty

  def doStop(): Unit = {
    maybeMutationObserver.foreach { mutationObserver =>
      mutationObserver.disconnect()
    }
  }

  def doStart(): Unit = {
    maybeMutationObserver.foreach { mutationObserver =>
      mutationObserver.observe(
        element.ref,
        options
      )
    }
  }

  override def bind(element: El): DynamicSubscription = {
    if (maybeMutationObserver.isDefined) {
      dom.console.error("mutationObserver can not be re-used")
    }
    this.element = element
    maybeMutationObserver = Some(
      new MutationObserver(
        callback = (entries, _) => {
          if (entries.nonEmpty) {
            onNext(entries.toSeq)
          }
        }
      )
    )
    super.bind(element)
  }

}

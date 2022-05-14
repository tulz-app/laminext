package io.laminext.core.binders

import com.raquo.airstream.ownership.DynamicSubscription
import com.raquo.laminar.nodes.ReactiveElement
import org.scalajs.dom
import org.scalajs.dom.MutationObserver
import org.scalajs.dom.MutationObserverInit
import org.scalajs.dom.MutationRecord

class MutationObserverBinder[El <: ReactiveElement[dom.HTMLElement]](
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

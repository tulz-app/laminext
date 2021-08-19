package io.laminext.core
package binders

import com.raquo.laminar.nodes.ReactiveElement
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.timers.SetTimeoutHandle

class SetTimeoutBinder[T, El <: ReactiveElement.Base](
  value: T,
  timeout: FiniteDuration,
  onNext: T => Unit
) extends BinderWithStartStop[El] {

  private var maybeTimeout: js.UndefOr[SetTimeoutHandle] = js.undefined

  def doStop(): Unit = {
    maybeTimeout.foreach { timer =>
      js.timers.clearTimeout(timer)
      maybeTimeout = js.undefined
    }
  }

  def doStart(): Unit = {
    if (maybeTimeout.isEmpty) {
      maybeTimeout = js.timers.setTimeout(timeout) {
        onNext(value)
      }
    }
  }

}

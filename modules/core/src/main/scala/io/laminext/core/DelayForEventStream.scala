package io.laminext.core

import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.core.WritableEventStream
import com.raquo.laminar.api.L._
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js

class DelayForEventStream[A](
  override protected val parent: EventStream[A],
  projectDelayMillis: A => FiniteDuration
) extends EventStream[A]
    with WritableEventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  /**
   * Async stream, so reset rank
   */
  override protected val topoRank: Int = 1

  override protected def onNext(nextValue: A, transaction: Transaction): Unit = {
    val _ = js.timers.setTimeout(projectDelayMillis(nextValue)) {
      val _ = new Transaction(fireValue(nextValue, _))
    }
  }

  override def onError(nextError: Throwable, transaction: Transaction): Unit  = {
    val _ = new Transaction(fireError(nextError, _))
  }
}

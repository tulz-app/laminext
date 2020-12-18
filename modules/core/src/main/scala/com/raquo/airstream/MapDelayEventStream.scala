package com.raquo.airstream

import com.raquo.airstream.core.Transaction
import com.raquo.airstream.eventstream.EventStream
import com.raquo.airstream.features.InternalNextErrorObserver
import com.raquo.airstream.features.SingleParentObservable

import scala.scalajs.js

class MapDelayEventStream[A](
  override protected val parent: EventStream[A],
  projectDelayMillis: A => Double
) extends EventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  /**
   * Async stream, so reset rank
   */
  override protected[airstream] val topoRank: Int = 1

  override protected[airstream] def onNext(nextValue: A, transaction: Transaction): Unit = {
    val _ = js.timers.setTimeout(projectDelayMillis(nextValue)) {
      val _ = new Transaction(fireValue(nextValue, _))
    }
  }

  override def onError(nextError: Throwable, transaction: Transaction): Unit = {
    val _ = new Transaction(fireError(nextError, _))
  }
}

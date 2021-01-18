package com.raquo.airstream.eventstream

import com.raquo.airstream.core.EventStream
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.core.WritableEventStream
import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable

class TakeEventStream[A](
  override protected val parent: EventStream[A],
  toTake: Int
) extends EventStream[A]
    with WritableEventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  private var taken: Int = 0

  override protected[airstream] val topoRank: Int = parent.topoRank + 1

  override protected[airstream] def onNext(nextValue: A, transaction: Transaction): Unit =
    if (taken < toTake) {
      taken = taken + 1
      fireValue(nextValue, transaction)
    }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

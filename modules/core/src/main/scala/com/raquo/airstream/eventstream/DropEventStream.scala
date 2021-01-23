package com.raquo.airstream.eventstream

import com.raquo.airstream.core.EventStream
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable

class DropEventStream[A](
  override protected val parent: EventStream[A],
  toDrop: Int
) extends EventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  private var dropped: Int = 0

  override protected[airstream] val topoRank: Int = parent.topoRank + 1

  override protected[airstream] def onNext(nextValue: A, transaction: Transaction): Unit =
    if (dropped < toDrop) {
      dropped = dropped + 1
    } else {
      fireValue(nextValue, transaction)
    }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

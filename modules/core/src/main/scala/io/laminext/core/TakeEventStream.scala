package io.laminext.core

import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable
import com.raquo.airstream.core.Protected
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.core.WritableEventStream
import com.raquo.laminar.api.L._

class TakeEventStream[A](
  override protected val parent: EventStream[A],
  toTake: Int
) extends EventStream[A]
    with WritableEventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  private var taken: Int = 0

  override protected val topoRank: Int = Protected.topoRank(parent) + 1

  override protected def onNext(nextValue: A, transaction: Transaction): Unit =
    if (taken < toTake) {
      taken = taken + 1
      fireValue(nextValue, transaction)
    }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

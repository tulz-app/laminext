package io.laminext.core

import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable
import com.raquo.airstream.core.Protected
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.core.WritableEventStream
import com.raquo.laminar.api.L._

class DropEventStream[A](
  override protected val parent: EventStream[A],
  toDrop: Int
) extends EventStream[A]
    with WritableEventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  private var dropped: Int = 0

  override protected val topoRank: Int = Protected.topoRank(parent) + 1

  override protected def onNext(nextValue: A, transaction: Transaction): Unit =
    if (dropped < toDrop) {
      dropped = dropped + 1
    } else {
      fireValue(nextValue, transaction)
    }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

package com.raquo.airstream.eventstream

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable

class TransitionsEventStream[A](
  override protected val parent: EventStream[A]
) extends EventStream[(Option[A], A)]
    with SingleParentObservable[A, (Option[A], A)]
    with InternalNextErrorObserver[A] {

  private var previous: Option[A] = Option.empty

  override protected[airstream] val topoRank: Int = parent.topoRank + 1

  override protected[airstream] def onNext(nextValue: A, transaction: Transaction): Unit = {
    fireValue((previous, nextValue), transaction)
    previous = Some(nextValue)
  }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

package com.raquo.airstream.eventstream

import com.raquo.laminar.api.L._
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentObservable

class LifecycleEventStream[A](
  override val parent: EventStream[A],
  startCallback: () => Unit,
  stopCallback: () => Unit,
) extends EventStream[A]
    with SingleParentObservable[A, A]
    with InternalNextErrorObserver[A] {

  override protected[airstream] val topoRank: Int = parent.topoRank + 1

  override protected[airstream] def onNext(nextParentValue: A, transaction: Transaction): Unit = {
    fireValue(nextParentValue, transaction)
  }

  override protected[airstream] def onError(nextError: Throwable, transaction: Transaction): Unit = {
    fireError(nextError, transaction)
  }

  override protected[this] def onStart(): Unit = {
    startCallback()
    super.onStart()
  }

  override protected[this] def onStop(): Unit = {
    stopCallback()
    super.onStop()
  }
}

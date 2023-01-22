package io.laminext.core

import com.raquo.airstream.common.InternalNextErrorObserver
import com.raquo.airstream.common.SingleParentStream
import com.raquo.airstream.core.Protected
import com.raquo.airstream.core.Transaction
import com.raquo.airstream.core.WritableStream
import com.raquo.laminar.api.L._

class TransitionsEventStream[A](
  override protected val parent: EventStream[A]
) extends EventStream[(Option[A], A)]
    with WritableStream[(Option[A], A)]
    with SingleParentStream[A, (Option[A], A)]
    with InternalNextErrorObserver[A] {

  private var previous: Option[A] = Option.empty

  override protected val topoRank: Int = Protected.topoRank(parent) + 1

  override protected def onNext(nextValue: A, transaction: Transaction): Unit = {
    fireValue((previous, nextValue), transaction)
    previous = Some(nextValue)
  }

  override def onError(nextError: Throwable, transaction: Transaction): Unit =
    fireError(nextError, transaction)

}

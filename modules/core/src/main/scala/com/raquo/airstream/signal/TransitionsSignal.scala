package com.raquo.airstream.signal

import com.raquo.airstream.core.Transaction
import com.raquo.airstream.features.InternalTryObserver
import com.raquo.airstream.features.SingleParentObservable

import scala.util.Success
import scala.util.Try

class TransitionsSignal[A](
  protected[this] val parent: Signal[A]
) extends Signal[(Option[A], A)]
    with SingleParentObservable[A, (Option[A], A)]
    with InternalTryObserver[A] {

  override protected[airstream] val topoRank: Int = parent.topoRank + 1

  private var previous: Option[A] = Option.empty

  override protected[airstream] def onTry(nextParentValue: Try[A], transaction: Transaction): Unit = {
    nextParentValue.fold(
      nextError => {
        fireError(nextError, transaction)
      },
      nextValue => {
        fireTry(Success((previous, nextValue)), transaction)
        previous = Some(nextValue)
      }
    )
  }

  override protected[this] def initialValue: Try[(Option[A], A)] = {
    val originalValue = parent.tryNow()
    originalValue.foreach { value =>
      previous = Some(value)
    }
    originalValue.map(value => (Option.empty[A], value))
  }

}

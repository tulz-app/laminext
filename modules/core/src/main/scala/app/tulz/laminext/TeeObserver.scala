package app.tulz.laminext

import com.raquo.airstream.core.Observer

import scala.util.Try

class TeeObserver[T](observers: Seq[Observer[T]]) extends Observer[T] {

  override def onNext(nextValue: T): Unit =
    observers.foreach(_.onNext(nextValue))

  override def onError(err: Throwable): Unit =
    observers.foreach(_.onError(err))

  override def onTry(nextValue: Try[T]): Unit =
    observers.foreach(_.onTry(nextValue))

}

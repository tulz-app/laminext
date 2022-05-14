package io.laminext.core

import com.raquo.laminar.api.L._
import org.scalajs.dom

final class StoredBoolean(name: String, initial: Boolean = true) {

  private val storageId = s"[StoreBoolean]$name"

  private val updateBus = new EventBus[Boolean => Boolean]

  val signal: Signal[Boolean] =
    updateBus.events
      .foldLeft[Boolean] {
        if (BrowserUtils.storageEnabled) {
          Option(dom.window.localStorage.getItem(storageId)).map(_.toBoolean).getOrElse(initial)
        } else {
          initial
        }
      } { case (current, update) =>
        val newValue = update(current)
        if (BrowserUtils.storageEnabled) {
          dom.window.localStorage.setItem(storageId, newValue.toString)
        }
        newValue
      }

  val toggleObserver: Observer[Any] = updateBus.writer.contramap(_ => !_)

  def toggle(): Unit = updateBus.writer.onNext(!_)

  val updateObserver: Observer[Boolean => Boolean] = updateBus.writer

  def update(f: Boolean => Boolean): Unit = updateBus.writer.onNext(f)

  val setObserver: Observer[Boolean] = updateBus.writer.contramap(newValue => _ => newValue)

  def set(newValue: Boolean): Unit = setObserver.onNext(newValue)

}

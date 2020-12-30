package io.laminext

import com.raquo.laminar.api.L._
import org.scalajs.dom.ext.LocalStorage

final class StoredString(name: String, initial: String) {

  private val storageId = s"[StoredString]$name"

  private val updateBus = new EventBus[String => String]()

  val signal: Signal[String] =
    updateBus.events
      .foldLeft[String](
        LocalStorage(storageId).getOrElse(initial)
      ) { case (current, update) =>
        val newValue = update(current)
        LocalStorage(storageId) = newValue
        newValue
      }

  val updateObserver: Observer[String => String] = updateBus.writer

  def update(f: String => String): Unit = updateBus.writer.onNext(f)

  def set(newValue: String): Unit = updateBus.writer.onNext(_ => newValue)

  def setObserver: Observer[String] = updateBus.writer.contramap(s => _ => s)

}

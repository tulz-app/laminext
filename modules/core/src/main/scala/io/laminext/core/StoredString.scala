package io.laminext.core

import com.raquo.laminar.api.L._
import org.scalajs.dom

final class StoredString(name: String, initial: String) {

  private val storageId = s"[StoredString]$name"

  private val updateBus = new EventBus[String => String]

  val signal: Signal[String] =
    updateBus.events
      .foldLeft[String](
        if (BrowserUtils.storageEnabled) {
          Option(dom.window.localStorage.getItem(storageId)).getOrElse(initial)
        } else {
          initial
        }
      ) { case (current, update) =>
        val newValue = update(current)
        if (BrowserUtils.storageEnabled) {
          dom.window.localStorage.setItem(storageId, newValue)
        }
        newValue
      }

  val updateObserver: Observer[String => String] = updateBus.writer

  def update(f: String => String): Unit = updateBus.writer.onNext(f)

  def set(newValue: String): Unit = updateBus.writer.onNext(_ => newValue)

  def setObserver: Observer[String] = updateBus.writer.contramap(s => _ => s)

}

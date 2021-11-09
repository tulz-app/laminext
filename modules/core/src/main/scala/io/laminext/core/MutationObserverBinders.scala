package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.MutationObserverBinder
import org.scalajs.dom.MutationObserverInit
import org.scalajs.dom.MutationRecord

import scala.scalajs.js

class MutationObserverBinders(
  childList: Boolean = false,
  attributes: Boolean = false,
  characterData: Boolean = false,
  subtree: Boolean = false,
  attributeOldValue: Boolean = false,
  characterDataOldValue: Boolean = false,
  attributeFilter: js.UndefOr[js.Array[String]] = js.undefined
) { self =>

  private def init = new MutationObserverInit {
    this.childList = self.childList
    this.attributes = self.attributes
    this.characterData = self.characterData
    this.subtree = self.subtree
    this.attributeOldValue = self.attributeOldValue
    this.characterDataOldValue = self.characterDataOldValue
    this.attributeFilter = self.attributeFilter
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.HTMLElement]](sink: Sink[Seq[MutationRecord]]): BinderWithStartStop[El] = {
    new MutationObserverBinder(sink.toObserver.onNext, init)
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.HTMLElement]](onNext: Seq[MutationRecord] => Unit): BinderWithStartStop[El] = {
    new MutationObserverBinder(onNext, init)
  }

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.HTMLElement]](sink: Sink[Seq[MutationRecord]]): BinderWithStartStop[El] = -->(sink)

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.HTMLElement]](onNext: Seq[MutationRecord] => Unit): BinderWithStartStop[El] = -->(onNext)

}

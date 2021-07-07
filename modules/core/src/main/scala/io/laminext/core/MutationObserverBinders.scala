package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core.binders.BinderWithStartStop
import io.laminext.core.binders.MutationObserverBinder
import org.scalajs.dom.raw.MutationObserverInit
import org.scalajs.dom.raw.MutationRecord

import scala.scalajs.js

class MutationObserverBinders(
  childList: Boolean = false,
  attributes: Boolean = false,
  characterData: Boolean = false,
  subtree: Boolean = false,
  attributeOldValue: Boolean = false,
  characterDataOldValue: Boolean = false,
  attributeFilter: js.UndefOr[js.Array[String]] = js.undefined
) {

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](sink: Sink[Seq[MutationRecord]]): BinderWithStartStop[El] = {
    new MutationObserverBinder(
      t => sink.toObserver.onNext(t),
      MutationObserverInit(
        childList = childList,
        attributes = attributes,
        characterData = characterData,
        subtree = subtree,
        attributeOldValue = attributeOldValue,
        characterDataOldValue = characterDataOldValue,
        attributeFilter = attributeFilter
      )
    )
  }

  @inline def -->[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](onNext: Seq[MutationRecord] => Unit): BinderWithStartStop[El] = {
    new MutationObserverBinder(
      onNext,
      MutationObserverInit(
        childList = childList,
        attributes = attributes,
        characterData = characterData,
        subtree = subtree,
        attributeOldValue = attributeOldValue,
        characterDataOldValue = characterDataOldValue,
        attributeFilter = attributeFilter
      )
    )
  }

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](sink: Sink[Seq[MutationRecord]]): BinderWithStartStop[El] = -->(sink)

  @inline def bind[El <: ReactiveElement[org.scalajs.dom.raw.HTMLElement]](onNext: Seq[MutationRecord] => Unit): BinderWithStartStop[El] = -->(onNext)

}

package io.laminext.ops.observable

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.core
import io.laminext.core.ConditionalChildInserter

final class ObservableOfBooleanOps(underlying: Observable[Boolean]) {

  def cls(s: String): Binder[ReactiveHtmlElement.Base] =
    L.cls <-- underlying.map(b => Seq(s -> b))

  def clsNot(s: String): Binder[ReactiveHtmlElement.Base] =
    L.cls <-- underlying.map(b => Seq(s -> !b))

  @inline def classSwitch(whenTrue: String, whenFalse: String): Binder[ReactiveHtmlElement.Base] =
    L.cls <-- underlying.map { bool =>
      Seq(
        whenTrue  -> bool,
        whenFalse -> !bool
      )
    }

  @inline def childWhenTrue(
    child: => Child
  ): Inserter[ReactiveHtmlElement.Base] =
    core.ConditionalChildInserter(underlying, child)

  @inline def childWhenFalse(
    child: => Child
  ): Inserter[ReactiveHtmlElement.Base] =
    core.ConditionalChildInserter(underlying.map(!_), child)

  @inline def doWhenTrue(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value) {
        callback
      }
    }

  @inline def doWhenFalse(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (!value) {
        callback
      }
    }

}

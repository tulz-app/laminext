package io.laminext.core
package ops.observable

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class ObservableOfBooleanOps(underlying: Observable[Boolean]) {

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
    ConditionalChildInserter(underlying, child)

  @inline def childWhenFalse(
    child: => Child
  ): Inserter[ReactiveHtmlElement.Base] =
    ConditionalChildInserter(underlying.map(!_), child)

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

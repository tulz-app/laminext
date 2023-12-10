package io.laminext.core
package ops.source

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SourceOfBooleanOps(underlying: Source[Boolean]) {

  @inline def classSwitch(whenTrue: String, whenFalse: String): Binder[ReactiveHtmlElement.Base] =
    L.cls <-- underlying.toObservable.map { bool =>
      Seq(
        whenTrue  -> bool,
        whenFalse -> !bool
      )
    }

  @inline def childWhenTrue(
    child: => ChildNode.Base
  ): Inserter =
    ConditionalChildInserter(underlying.toObservable, child)

  @inline def childWhenFalse(
    child: => ChildNode.Base
  ): Inserter =
    ConditionalChildInserter(underlying.toObservable.map(!_), child)

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

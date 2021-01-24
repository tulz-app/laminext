package io.laminext.core
package ops.observable

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class ObservableOfOptionOps[A](val underlying: Observable[Option[A]]) {

  @inline def childIfEmpty(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isEmpty), child)

  @inline def childIfDefined(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isDefined), child)

  @inline def doWhenDefined(
    callback: () => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isDefined) {
        callback()
      }
    }

  @inline def doWhenEmpty(
    callback: () => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isEmpty) {
        callback()
      }
    }

}

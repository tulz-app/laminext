package io.laminext.core
package ops.observable

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class ObservableOfEitherOps[A, B](val underlying: Observable[Either[A, B]]) {

  @inline def childIfLeft(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isLeft), child)

  @inline def childIfRight(child: => Child): Inserter[ReactiveElement.Base] =
    ConditionalChildInserter(underlying.map(_.isRight), child)

  @inline def doWhenLeft(
    callback: () => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isLeft) {
        callback()
      }
    }

  @inline def doWhenRight(
    callback: () => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isRight) {
        callback()
      }
    }

}

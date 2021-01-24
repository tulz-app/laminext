package io.laminext.ops.observable

import com.raquo.airstream.core.Observable
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.core
import io.laminext.core.ConditionalChildInserter

final class ObservableOfEitherOps[A, B](val underlying: Observable[Either[A, B]]) {

  @inline def childIfLeft(child: => Child): Inserter[ReactiveElement.Base] =
    core.ConditionalChildInserter(underlying.map(_.isLeft), child)

  @inline def childIfRight(child: => Child): Inserter[ReactiveElement.Base] =
    core.ConditionalChildInserter(underlying.map(_.isRight), child)

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

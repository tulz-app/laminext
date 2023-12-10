package io.laminext.core
package ops.source

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SourceOfEitherOps[A, B](val underlying: Source[Either[A, B]]) {

  @inline def childIfLeft(child: => ChildNode.Base): Inserter =
    ConditionalChildInserter(underlying.toObservable.map(_.isLeft), child)

  @inline def childIfRight(child: => ChildNode.Base): Inserter =
    ConditionalChildInserter(underlying.toObservable.map(_.isRight), child)

  @inline def doWhenLeft(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isLeft) {
        callback
      }
    }

  @inline def doWhenRight(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isRight) {
        callback
      }
    }

}

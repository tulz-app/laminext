package io.laminext.core
package ops.source

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SourceOfOptionOps[A](val underlying: Source[Option[A]]) {

  @inline def childIfEmpty(child: => ChildNode.Base): Inserter =
    ConditionalChildInserter(underlying.toObservable.map(_.isEmpty), child)

  @inline def childIfDefined(child: => ChildNode.Base): Inserter =
    ConditionalChildInserter(underlying.toObservable.map(_.isDefined), child)

  @inline def doWhenDefined(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isDefined) {
        callback
      }
    }

  @inline def doWhenEmpty(
    callback: => Unit
  ): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value.isEmpty) {
        callback
      }
    }

}

package io.laminext.core
package ops.signal

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SignalOfBooleanOps(underlying: Signal[Boolean]) {

  @inline def unary_! : Signal[Boolean] = underlying.map(!_)

  @inline def not: Signal[Boolean] = underlying.map(!_)

  @inline def ||(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l || r
  }

  @inline def ||(that: Signal[Boolean]): Signal[Boolean] =
    underlying.combineWithFn(that)(_ || _)

  @inline def &&(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l && r
  }

  @inline def &&(that: Signal[Boolean]): Signal[Boolean] =
    underlying.combineWithFn(that)(_ || _)

  @inline def classSwitch(whenTrue: String, whenFalse: String): Binder[ReactiveHtmlElement.Base] =
    cls <-- underlying.map { bool =>
      Seq(
        whenTrue  -> bool,
        whenFalse -> !bool
      )
    }

  @inline def switch[T](whenTrue: T, whenFalse: T): Signal[T] =
    underlying.map(if (_) whenTrue else whenFalse)

}

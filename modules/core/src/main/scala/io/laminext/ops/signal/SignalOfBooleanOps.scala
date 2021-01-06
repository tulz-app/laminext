package io.laminext.ops.signal

import io.laminext.ConditionalChildInserter
import com.raquo.airstream.signal.Signal
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SignalOfBooleanOps(underlying: Signal[Boolean]) {

  @inline def unary_! : Signal[Boolean] = underlying.map(!_)

  @inline def not: Signal[Boolean] = underlying.map(!_)

  @inline def ||(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l || r
  }

  @inline def ||(that: Signal[Boolean]): Signal[Boolean] =
    underlying.combineWith(that)(_ || _)

  @inline def &&(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l && r
  }

  @inline def &&(that: Signal[Boolean]): Signal[Boolean] =
    underlying.combineWith(that)(_ || _)

  @inline def classSwitch(whenTrue: String, whenFalse: String): Binder[ReactiveHtmlElement.Base] =
    cls <-- underlying.map { bool =>
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

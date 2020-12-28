package app.tulz.laminext.ops.signal

import app.tulz.laminext.ConditionalChildInserter
import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class SignalOfBooleanOps(underlying: Signal[Boolean]) {

  @inline def unary_! : Signal[Boolean] = underlying.map(!_)

  @inline def not: Signal[Boolean] = underlying.map(!_)

  @inline def ||(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l || r
  }

  @inline def ||(that: Signal[Boolean]): Signal[Boolean] = underlying.combineWith(that).map { case (l, r) =>
    l || r
  }

  @inline def &&(r: Boolean): Signal[Boolean] = underlying.map { l =>
    l && r
  }

  @inline def &&(that: Signal[Boolean]): Signal[Boolean] = underlying.combineWith(that).map { case (l, r) =>
    l && r
  }

  @inline def childWhenTrue(child: => Child): Inserter[ReactiveHtmlElement.Base] =
    ConditionalChildInserter(underlying, child)

  @inline def childWhenFalse(child: => Child): Inserter[ReactiveHtmlElement.Base] =
    ConditionalChildInserter(underlying.map(!_), child)

  @inline def doWhenTrue(callback: () => Unit): Binder[ReactiveHtmlElement.Base] =
    underlying --> { value =>
      if (value) {
        callback()
      }
    }

  @inline def doWhenFalse(callback: () => Unit): Binder[ReactiveHtmlElement.Base] = {
    underlying --> { value =>
      if (!value) {
        callback()
      }
    }
  }

}

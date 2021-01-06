package io.laminext.ops.observable

import com.raquo.laminar.api.L
import com.raquo.laminar.api.L._
import com.raquo.airstream.core.Observable
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveHtmlElement

final class ObservableOfBooleanOps(underlying: Observable[Boolean]) {

  def cls(s: String): Observable[Seq[(String, Boolean)]] =
    underlying.map(b => Seq(s -> b))

  def clsNot(s: String): Observable[Seq[(String, Boolean)]] =
    underlying.map(b => Seq(s -> !b))

  @inline def classSwitch(whenTrue: String, whenFalse: String): Binder[ReactiveHtmlElement.Base] =
    L.cls <-- underlying.map { bool =>
      Seq(
        whenTrue  -> bool,
        whenFalse -> !bool
      )
    }

}

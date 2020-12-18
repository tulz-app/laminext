package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Signal
import com.raquo.domtypes.generic.Modifier
import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ChildNode
import com.raquo.laminar.nodes.ReactiveHtmlElement
import org.scalajs.dom

final class SignalOfBooleanOps(s: Signal[Boolean]) {

  def unary_! : Signal[Boolean] = s.map(!_)

  def not: Signal[Boolean] = s.map(!_)

  def ||(that: Signal[Boolean]): Signal[Boolean] = s.combineWith(that).map { case (l, r) =>
    l || r
  }

  def &&(that: Signal[Boolean]): Signal[Boolean] = s.combineWith(that).map { case (l, r) =>
    l && r
  }

  @inline def renderIfTrue(node: ChildNode[dom.Node]): Modifier[HtmlElement] =
    child.maybe <-- s.map { b =>
      if (b) {
        Some(node)
      } else {
        Option.empty
      }
    }

  @inline def renderIfFalse(node: ChildNode[dom.Node]): Modifier[HtmlElement] =
    child.maybe <-- s.map { b =>
      if (!b) {
        Some(node)
      } else {
        Option.empty
      }
    }

  def whenTrue(callback: () => Unit): Binder[ReactiveHtmlElement.Base] =
    new SignalOps(s).bind { value =>
      if (value) {
        callback()
      }
    }

}

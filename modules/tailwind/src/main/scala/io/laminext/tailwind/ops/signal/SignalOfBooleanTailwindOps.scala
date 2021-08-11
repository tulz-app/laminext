package io.laminext.tailwind.ops.signal

import com.raquo.laminar.api.L._
import io.laminext.syntax.core._
import io.laminext.syntax.tailwind._

final class SignalOfBooleanTailwindOps(underlying: Signal[Boolean]) {

  @inline def switchVisibility(whenTrue: => HtmlElement, whenFalse: => HtmlElement): Mod[HtmlElement] =
    nodeSeq(
      whenTrue.visibleIf(underlying),
      whenFalse.hiddenIf(underlying)
    )

}

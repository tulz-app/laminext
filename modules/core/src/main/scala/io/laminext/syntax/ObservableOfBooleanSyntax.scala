package io.laminext.syntax

import com.raquo.airstream.core.Observable
import io.laminext.ops.observable.ObservableOfBooleanOps

trait ObservableOfBooleanSyntax {

  implicit def syntaxObservableOfBooleanOps(
    o: Observable[Boolean]
  ): ObservableOfBooleanOps = new ObservableOfBooleanOps(o)

}

package io.laminext.syntax

import com.raquo.airstream.core.Observable
import io.laminext.core.ops.source.SourceOfBooleanOps

trait ObservableOfBooleanSyntax {

  implicit def syntaxObservableOfBooleanOps(
    o: Observable[Boolean]
  ): SourceOfBooleanOps = new SourceOfBooleanOps(o)

}

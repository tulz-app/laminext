package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.source.SourceOfOptionOps
import io.laminext.core.ops.source.SourceOps

trait ObservableSyntax {

  implicit def syntaxObservable[A](
    s: Observable[A]
  ): SourceOps[A] = new SourceOps[A](s)

}

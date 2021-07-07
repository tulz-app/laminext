package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.source.SourceOfOptionOps

trait ObservableOfOptionSyntax {

  implicit def syntaxObservableOfOption[A](
    s: Observable[Option[A]]
  ): SourceOfOptionOps[A] = new SourceOfOptionOps[A](s)

}

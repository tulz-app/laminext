package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.observable.ObservableOfOptionOps

trait ObservableOfOptionSyntax {

  implicit def syntaxObservableOfOption[A](
    s: Observable[Option[A]]
  ): ObservableOfOptionOps[A] = new ObservableOfOptionOps[A](s)

}

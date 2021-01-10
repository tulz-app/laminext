package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.ops.observable.ObservableOfOptionOps
import io.laminext.ops.observable.ObservableOps

trait ObservableSyntax {

  implicit def syntaxObservable[A](
    s: Observable[A]
  ): ObservableOps[A] = new ObservableOps[A](s)

}

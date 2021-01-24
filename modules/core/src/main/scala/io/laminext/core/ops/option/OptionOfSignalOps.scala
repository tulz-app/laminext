package io.laminext.core
package ops.option

import com.raquo.laminar.api.L._

final class OptionOfSignalOps[A](o: Option[Signal[A]]) {

  def lift: Signal[Option[A]] = o.fold(Val(Option.empty[A]): Signal[Option[A]])(_.map(Some(_)))

}

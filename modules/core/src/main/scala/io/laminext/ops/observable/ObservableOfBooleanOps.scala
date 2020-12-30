package io.laminext.ops.observable

import com.raquo.airstream.core.Observable

final class ObservableOfBooleanOps(o: Observable[Boolean]) {

  def cls(s: String): Observable[Seq[(String, Boolean)]] =
    o.map(b => Seq(s -> b))

}

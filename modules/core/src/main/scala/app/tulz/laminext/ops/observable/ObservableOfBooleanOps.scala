package app.tulz.laminext.ops.observable

import com.raquo.airstream.core.Observable

final class ObservableOfBooleanOps(o: Observable[Boolean]) {

  def cls(s: String): Observable[List[(String, Boolean)]] =
    o.map(b => s -> b :: Nil)

}

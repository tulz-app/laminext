package app.tulz.laminext.ops.signal

import com.raquo.airstream.signal.Signal

final class SignalOfEitherOps[L, R](s: Signal[Either[L, R]]) {

  @inline def isLeft: Signal[Boolean] = s.map(_.isLeft)

  @inline def isRight: Signal[Boolean] = s.map(_.isRight)

}

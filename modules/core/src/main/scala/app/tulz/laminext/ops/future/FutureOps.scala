package app.tulz.laminext.ops.future

import app.tulz.tuplez.Composition
import com.raquo.laminar.api.L._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration._

final class FutureOps[A](f: => Future[A]) {

  def delayed(delay: FiniteDuration): Future[A] =
    f.flatMap { value =>
      FutureCompanionOps.delayed(delay)(value)
    }

  @inline def stream: EventStream[A] = EventStream.fromFuture(f)

  def combine[B](otherFuture: Future[B])(implicit composition: Composition[A, B]): Future[composition.Composed] = {
    f.zipWith(otherFuture)(composition.compose)
  }

}

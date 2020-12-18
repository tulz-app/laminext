package app.tulz.laminext.ops.future

import app.tulz.tuplez.Composition
import com.raquo.laminar.api.L._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class FutureOps[A](f: => Future[A]) {

  def delayed(millis: Int): Future[A] =
    f.flatMap { value =>
      FutureCompanionOps.delayed(millis)(value)
    }

  def stream: EventStream[A] = EventStream.fromFuture(f)

  def zipC[AA >: A, B](otherFuture: Future[B])(implicit composition: Composition[AA, B]): Future[composition.Composed] = {
    f.zipWith(otherFuture)(composition.compose)
  }

}

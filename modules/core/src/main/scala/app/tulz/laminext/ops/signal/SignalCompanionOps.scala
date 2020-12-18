package app.tulz.laminext.ops.signal

import com.raquo.laminar.api.L._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

final class SignalCompanionOps {

  def fromFuture[A](future: Future[A]): Signal[Option[A]] = {
    val resultVar = Var(Option.empty[A])
    future.foreach { result =>
      resultVar.writer.onNext(Some(result))
    }
    resultVar.signal
  }

  def seq[A](signals: Seq[Signal[A]]): Signal[Seq[A]] =
    signals.foldLeft[Signal[Seq[A]]](Val(Seq.empty))((acc, next) => acc.combineWith(next).map2(_ :+ _))

}

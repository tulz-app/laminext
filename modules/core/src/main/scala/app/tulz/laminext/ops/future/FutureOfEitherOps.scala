package app.tulz.laminext.ops.future

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

final class FutureOfEitherOps[A, B](f: => Future[Either[A, B]]) {

  def mapRight[BB >: B, C](project: B => C)(implicit ec: ExecutionContext): Future[Either[A, C]] = {
    f.map(_.map(project))
  }

}

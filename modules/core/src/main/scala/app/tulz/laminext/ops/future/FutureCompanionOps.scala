package app.tulz.laminext.ops.future

import scala.concurrent.Future
import scala.concurrent.Promise
import scala.scalajs.js
import scala.concurrent.duration._

object FutureCompanionOps {

  def delayed[A](delay: FiniteDuration)(value: => A): Future[A] = {
    val promise = Promise[A]()
    js.timers.setTimeout(delay.toMillis.toDouble) {
      promise.success(value)
    }
    promise.future
  }

}

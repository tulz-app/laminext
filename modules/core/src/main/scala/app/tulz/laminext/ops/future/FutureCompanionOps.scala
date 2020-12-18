package app.tulz.laminext.ops.future

import scala.concurrent.Future
import scala.concurrent.Promise
import scala.scalajs.js

object FutureCompanionOps {

  def delayed[A](millis: Int)(value: => A): Future[A] = {
    val promise = Promise[A]()
    js.timers.setTimeout(millis) {
      promise.success(value)
    }
    promise.future
  }

}

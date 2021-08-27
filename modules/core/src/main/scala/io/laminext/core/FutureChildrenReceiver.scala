package io.laminext.core

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import scala.concurrent.Future

private[laminext] object FutureChildrenReceiver {

  @inline def <--(future: Future[Children]): Inserter[ReactiveElement.Base] =
    children <-- EventStream.fromFuture(future)

}

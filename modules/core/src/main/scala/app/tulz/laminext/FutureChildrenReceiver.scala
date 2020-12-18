package app.tulz.laminext

import com.raquo.laminar.api.L._
import com.raquo.airstream.eventstream.EventStream
import com.raquo.laminar.modifiers.ChildrenInserter
import com.raquo.laminar.modifiers.Inserter
import com.raquo.laminar.nodes.ReactiveElement

import scala.concurrent.Future

object FutureChildrenReceiver {

  def <--($nodes: Future[Children]): Inserter[ReactiveElement.Base] = {
    ChildrenInserter[ReactiveElement.Base](_ => EventStream.fromFuture($nodes), initialInsertContext = None)
  }

}

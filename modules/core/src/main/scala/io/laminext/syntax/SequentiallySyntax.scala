package io.laminext.syntax

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement
import io.laminext.core._
import org.scalajs.dom

import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js

trait SequentiallySyntax {

  def sequentially[In, Out](
    tasks: EventStream[In],
    persistent: Boolean = false
  )(run: In => EventStream[Out]): TaskQueue[In, Out] = TaskQueue(tasks, run, persistent)

  def sequentially[In, Out](
    tasks: Seq[In],
    persistent: Boolean
  )(run: In => EventStream[Out]): TaskQueue[In, Out] = TaskQueue(EventStream.fromSeq(tasks), run, persistent)

}

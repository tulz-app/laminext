package io.laminext.core

import com.raquo.airstream.ownership.Subscription
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

import scala.collection.mutable
import scala.scalajs.js

class TaskQueue[In, Out] private (tasks: EventStream[In], run: In => EventStream[Out], persistent: Boolean) {

  import TaskQueue.QueueState
  import TaskQueue.QueueEvent

  private var bindsCount                                   = 0
  private val queue: mutable.ArrayDeque[In]                = mutable.ArrayDeque.empty
  private val (events, submitEvent)                        = EventStream.fromCallback[QueueEvent]
  private val (_output, submitOutput)                      = EventStream.fromCallback[Out]
  private var state: QueueState                            = TaskQueue.QueueState.Stopped
  private var tasksSubscription: js.UndefOr[Subscription]  = js.undefined
  private var eventsSubscription: js.UndefOr[Subscription] = js.undefined
  private var runSubscription: js.UndefOr[Subscription]    = js.undefined
  val output: EventStream[Out]                             = _output

  private def binderStarted(): Unit = {
    if (bindsCount == 0) {
      implicit val owner: Owner = unsafeWindowOwner
      tasksSubscription = tasks.foreach { task =>
        queue.append(task)
        submitEvent(QueueEvent.Pull)
      }
      eventsSubscription = events.foreach { case QueueEvent.Pull =>
        if (state == QueueState.Idle) {
          queue.removeHeadOption() match {
            case Some(task) =>
              state = QueueState.Busy
              runSubscription = run(task).foreach { out =>
                if (state == QueueState.Busy) {
                  submitOutput(out)
                  runSubscription.foreach(_.kill())
                  runSubscription = js.undefined
                  state = QueueState.Idle
                  submitEvent(QueueEvent.Pull)
                }
              }

            case None => // noop
          }

        }
      }
      state = QueueState.Idle
      submitEvent(QueueEvent.Pull)
    }
    bindsCount += 1
  }

  private def binderStopped(): Unit = {
    bindsCount -= 1
    if (bindsCount == 0) {
      state = QueueState.Stopped
      if (!persistent) {
        queue.clear()
      }
      tasksSubscription.foreach(_.kill())
      tasksSubscription = js.undefined
      eventsSubscription.foreach(_.kill())
      eventsSubscription = js.undefined
      runSubscription.foreach(_.kill())
      runSubscription = js.undefined
    }
  }

  def bind[El <: ReactiveElement.Base]: Binder[El] =
    (element: El) =>
      ReactiveElement.bindSubscriptionUnsafe(element) { ctx =>
        binderStarted()
        new Subscription(
          ctx.owner,
          cleanup = () => {
            binderStopped()
          }
        )
      }

}

object TaskQueue {

  def apply[In, Out](
    tasks: EventStream[In],
    run: In => EventStream[Out],
    persistent: Boolean
  ): TaskQueue[In, Out] = new TaskQueue(tasks, run, persistent)

  sealed private[TaskQueue] trait QueueState extends Product with Serializable
  private[TaskQueue] object QueueState {

    case object Stopped extends QueueState
    case object Idle    extends QueueState
    case object Busy    extends QueueState

  }

  sealed private[TaskQueue] trait QueueEvent extends Product with Serializable
  private[TaskQueue] object QueueEvent {

    case object Pull extends QueueEvent

  }

}

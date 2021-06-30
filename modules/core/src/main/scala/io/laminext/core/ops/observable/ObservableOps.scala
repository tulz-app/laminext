package io.laminext.core
package ops.observable

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

final class ObservableOps[A](underlying: Observable[A]) {

  @inline def bind(onNext: A => Unit): Binder[ReactiveElement.Base] = underlying --> onNext

  @inline def bindCollect(onNext: PartialFunction[A, Unit]): Binder[ReactiveElement.Base] =
    underlying --> { a =>
      if (onNext.isDefinedAt(a)) {
        onNext(a)
      }
    }

  def addSwitchingObserver(observers: Source[Observer[A]]): Modifier[ReactiveElement.Base] = {
    onMountBind { ctx =>
      var previousSubscription = Option.empty[Subscription]
      observers --> { observer =>
        previousSubscription.foreach(_.kill())
        previousSubscription = Some(underlying.addObserver(observer)(ctx.owner))
      }
    }
  }

  def addSwitchingObserverOpt(observers: Source[Option[Observer[A]]]): Modifier[ReactiveElement.Base] = {
    onMountBind { ctx =>
      var previousSubscription = Option.empty[Subscription]
      observers --> { observer =>
        previousSubscription.foreach(_.kill())
        previousSubscription = observer.map(observer => underlying.addObserver(observer)(ctx.owner))
      }
    }
  }

}

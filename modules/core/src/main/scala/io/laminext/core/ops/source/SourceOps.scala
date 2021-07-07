package io.laminext.core
package ops.source

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveElement

final class SourceOps[A](underlying: Source[A]) {

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
        previousSubscription = Some(underlying.toObservable.addObserver(observer)(ctx.owner))
      }
    }
  }

  def addOptionalSwitchingObserver(observers: Source[Option[Observer[A]]]): Modifier[ReactiveElement.Base] = {
    onMountBind { ctx =>
      var previousSubscription = Option.empty[Subscription]
      observers --> { observer =>
        previousSubscription.foreach(_.kill())
        previousSubscription = observer.map(observer => underlying.toObservable.addObserver(observer)(ctx.owner))
      }
    }
  }

}

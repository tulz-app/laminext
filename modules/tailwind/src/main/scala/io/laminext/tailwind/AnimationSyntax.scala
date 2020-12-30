package io.laminext.tailwind

import io.laminext.ui.animation.Animation
import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait AnimationSyntax {

  object animate {

    @inline def apply(
      animationClass: Signal[Option[String]],
      iterations: Int = 1,
      endObserver: Observer[String] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] = Animation(animationClass, iterations, endObserver)

  }

}

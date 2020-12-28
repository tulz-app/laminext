package app.tulz.tailwind

import app.tulz.ui.animation.Animation
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

object AnimationSyntax extends AnimationSyntax

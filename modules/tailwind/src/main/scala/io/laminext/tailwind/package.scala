package io.laminext

import com.raquo.laminar.api.L._
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.ui.animation.Animation

package object tailwind {

  object TW {

    object transition extends TransitionSyntax

    @inline def animation(
      animationClass: Signal[Option[String]],
      iterations: Int = 1,
      endObserver: Observer[String] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] = Animation(animationClass, iterations, endObserver)

  }

}

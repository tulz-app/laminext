package io.laminext.tailwind

import theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.ui.transition.Transition
import io.laminext.ui.transition.TransitionConfig
import com.raquo.laminar.nodes.ReactiveHtmlElement

trait TransitionSyntax {

  object transition {

    @inline def opacityAndScale(
      show: Signal[Boolean],
      observer: Observer[Boolean] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] =
      apply(show, theme.Transition.opacityAndScale, observer)

    @inline def opacity(
      show: Signal[Boolean],
      observer: Observer[Boolean] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] =
      apply(show, theme.Transition.opacity, observer)

    @inline def apply(
      show: Signal[Boolean],
      config: TransitionConfig = Theme.current.transition.default,
      observer: Observer[Boolean] = Observer.empty
    ): Modifier[ReactiveHtmlElement.Base] = Transition(show, config, observer)

  }

}

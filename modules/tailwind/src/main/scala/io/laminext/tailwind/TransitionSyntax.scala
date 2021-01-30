package io.laminext.tailwind

import theme.Theme
import com.raquo.laminar.api.L._
import io.laminext.ui.transition.Transition
import io.laminext.ui.transition.TransitionConfig

trait TransitionSyntax {

  @inline def opacityAndScale(
    show: Signal[Boolean],
    observer: Observer[Boolean] = Observer.empty
  ): Modifier[HtmlElement] =
    apply(show, theme.Theme.current.transition.opacityAndScale, observer)

  @inline def opacity(
    show: Signal[Boolean],
    observer: Observer[Boolean] = Observer.empty
  ): Modifier[HtmlElement] =
    apply(show, theme.Theme.current.transition.opacity, observer)

  @inline def apply(
    show: Signal[Boolean],
    config: TransitionConfig = Theme.current.transition.default,
    observer: Observer[Boolean] = Observer.empty
  ): Modifier[HtmlElement] = Transition(show, config, observer)

}

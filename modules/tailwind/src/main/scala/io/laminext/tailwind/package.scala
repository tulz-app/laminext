package io.laminext

package object tailwind {

  object syntax extends BaseSyntax with ButtonSyntax with CardSyntax

  object TW extends AnimationSyntax {

    object transition extends TransitionSyntax

  }

}

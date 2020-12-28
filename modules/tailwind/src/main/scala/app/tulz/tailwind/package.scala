package app.tulz

import app.tulz.laminar.ext.AmAny

package object tailwind extends BaseSyntax with ButtonSyntax with CardSyntax with TransitionSyntax with AnimationSyntax {

  val base: BaseSyntax = BaseSyntax

  val transitions: TransitionSyntax = TransitionSyntax

  val animations: AnimationSyntax = AnimationSyntax

  val buttons: ButtonSyntax = ButtonSyntax

  val cards: CardSyntax = CardSyntax

  trait AmButtonExpectSizeOrGroup           extends AmAny
  trait AmButtonExpectsStyle                extends AmAny
  trait AmButtonGroupExpectsSize            extends AmAny
  trait AmButtonGroupExpectsPosition        extends AmAny
  trait AmButtonGroupExpectsStyle           extends AmAny
  trait AmButtonFillExpectColor             extends AmAny
  trait AmButtonOutlineExpectColor          extends AmAny
  trait AmButtonTransparentExpectColor      extends AmAny
  trait AmButtonGroupFillExpectColor        extends AmAny
  trait AmButtonGroupOutlineExpectColor     extends AmAny
  trait AmButtonGroupTransparentExpectColor extends AmAny
  trait AmButtonWithStyle                   extends AmAny

  trait AmCard       extends AmAny
  trait AmCardHeader extends AmAny
  trait AmCardBody   extends AmAny
  trait AmCardFooter extends AmAny
  trait AmCardTitle  extends AmAny

}

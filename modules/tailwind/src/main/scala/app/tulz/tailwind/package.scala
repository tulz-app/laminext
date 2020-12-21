package app.tulz

import app.tulz.laminar.ext.AmAny

package object tailwind extends ButtonSyntax with TransitionSyntax {

  object transitions extends TransitionSyntax
  object buttons     extends ButtonSyntax

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

}

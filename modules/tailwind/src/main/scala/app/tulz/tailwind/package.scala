package app.tulz

package object tailwind extends BaseSyntax with ButtonSyntax with CardSyntax with TransitionSyntax with AnimationSyntax {

  val base: BaseSyntax = BaseSyntax

  val transitions: TransitionSyntax = TransitionSyntax

  val animations: AnimationSyntax = AnimationSyntax

  val buttons: ButtonSyntax = ButtonSyntax

  val cards: CardSyntax = CardSyntax

}

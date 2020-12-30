package io.laminext.syntax

import io.laminext.ops.option.OptionOps

trait OptionSyntax {

  implicit def syntaxOption[A](o: Option[A]): OptionOps[A] = new OptionOps[A](o)

}

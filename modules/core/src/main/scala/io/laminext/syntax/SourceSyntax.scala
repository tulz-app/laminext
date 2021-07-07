package io.laminext.syntax

import com.raquo.laminar.api.L._
import io.laminext.core.ops.source.SourceOps

trait SourceSyntax {

  implicit def syntaxSource[A](
    s: Source[A]
  ): SourceOps[A] = new SourceOps[A](s)

}

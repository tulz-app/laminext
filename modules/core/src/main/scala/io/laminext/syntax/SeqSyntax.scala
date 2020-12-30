package io.laminext.syntax

import io.laminext.ops.iterable.SeqOps

import scala.collection.generic.IsSeq

trait SeqSyntax {

  implicit def syntaxSeq[Repr](coll: Repr)(implicit
    seq: IsSeq[Repr]
  ): SeqOps[Repr, seq.type] = new SeqOps(coll, seq)

}

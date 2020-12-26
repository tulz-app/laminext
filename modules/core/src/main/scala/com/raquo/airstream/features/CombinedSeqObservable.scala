package com.raquo.airstream.features

import com.raquo.airstream.core.AirstreamError.CombinedError

import scala.util.Failure
import scala.util.Success
import scala.util.Try

object CombinedSeqObservable {

  def guardedSeqCombinator[A, Out](trys: Seq[Try[A]], toOut: Seq[A] => Out): Try[Out] =
    if (trys.forall(_.isSuccess)) {
      Success(toOut(trys.map(_.get)))
    } else {
      Failure(CombinedError(trys.map(_.failed.toOption)))
    }

}

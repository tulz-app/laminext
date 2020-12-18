package app.tulz.laminar.ext

package object cats {

//  implicit class EventStreamOfValidatedNelExt[A, B](val s: EventStream[ValidatedNel[A, B]]) extends AnyVal {
//
//    def combineInvalid(other: EventStream[ValidatedNel[A, B]]): EventStream[ValidatedNel[A, Unit]] =
//      s.combineWith(other).map { case (first, second) =>
//        first.map(_ => ()).combine(second.map(_ => ()))
//      }
//  }
//
//  implicit class EventStreamOfValidatedExt[A, B](val s: EventStream[Validated[A, B]]) extends AnyVal {
//
//    def isValid: EventStream[Boolean] = s.map(_.isValid)
//
//    def isInvalid: EventStream[Boolean] = s.map(_.isInvalid)
//
//    def valid: EventStream[B] = s.collect { case Validated.Valid(valid) =>
//      valid
//    }
//
//    def invalid: EventStream[A] = s.collect { case Validated.Invalid(invalid) =>
//      invalid
//    }
//
//  }

}

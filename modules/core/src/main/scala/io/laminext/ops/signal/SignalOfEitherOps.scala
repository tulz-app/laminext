package io.laminext.ops.signal

import com.raquo.laminar.api.L._
import com.raquo.laminar.modifiers.Binder
import com.raquo.laminar.nodes.ReactiveElement
import com.raquo.laminar.nodes.ReactiveHtmlElement
import io.laminext.core.ConditionalChildInserter

final class SignalOfEitherOps[A, B](underlying: Signal[Either[A, B]]) {

  @inline def isLeft: Signal[Boolean] = underlying.map(_.isLeft)

  @inline def isRight: Signal[Boolean] = underlying.map(_.isRight)

  @inline def eitherRightMap[C](project: B => C): Signal[Either[A, C]] = underlying.map(_.map(project))

  @inline def eitherLeftMap[C](project: A => C): Signal[Either[C, B]] = underlying.map(_.left.map(project))

  @inline def maybeRightMap[C](project: B => C): Signal[Option[C]] = underlying.map(_.toOption.map(project))

  @inline def maybeLeftMap[C](project: A => C): Signal[Option[C]] = underlying.map(_.left.toOption.map(project))

  @inline def eitherToOption: Signal[Option[B]] = underlying.map(_.toOption)

  @inline def eitherLeftToOption: Signal[Option[A]] = underlying.map(_.left.toOption)

  @inline def eitherFold[C](fa: A => C, fb: B => C): Signal[C] = underlying.map(_.fold(fa, fb))

  @inline def eitherSwap: Signal[Either[B, A]] = underlying.map(_.swap)

  def rightFlatMap[C](mapping: B => Signal[Either[A, C]]): Signal[Either[A, C]] = underlying.flatMap {
    case Right(r) => mapping(r)
    case Left(l)  => Val(Left(l))
  }

  def leftFlatMap[C](mapping: A => Signal[Either[C, B]]): Signal[Either[C, B]] = underlying.flatMap {
    case Right(r) => Val(Right(r))
    case Left(l)  => mapping(l)
  }

}

package io.laminext.fetch.circe

import io.circe.parser
import io.circe.Decoder

import scala.collection.mutable

class StatusDependantDecoderBuilder[A] {

  private val decoders: mutable.Map[Int, Decoder[_]] = mutable.Map.empty

  def status[AA <: A: Decoder](statusCode: Int): StatusDependantDecoderBuilder[A] = {
    decoders.put(statusCode, implicitly[Decoder[AA]])
    this
  }

  private[circe] def build: StatusDependantDecoder[A] = new StatusDependantDecoder[A](decoders.toMap.asInstanceOf[Map[Int, Decoder[A]]])

}

private[circe] class StatusDependantDecoder[A](decoders: Map[Int, Decoder[A]]) {

  def decode(text: String, statusCode: Int): Either[Throwable, A] = {
    decoders.get(statusCode) match {
      case None          => Left(new UnexpectedStatusCode(statusCode))
      case Some(decoder) =>
        parser.parse(text) match {
          case Left(error) => Left(error)
          case Right(json) => decoder.decodeJson(json)
        }
    }
  }

}

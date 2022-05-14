package io.laminext.fetch
package circe

import com.raquo.laminar.api.L._
import io.circe._
import io.circe.parser
import org.scalajs.dom.Response

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits._

class FetchEventStreamBuilderCirceOps(underlying: FetchEventStreamBuilder) {

  private def decodeResponse[A](response: Response)(implicit decoder: Decoder[A], ec: ExecutionContext): Future[A] =
    response.text().flatMap { text =>
      parser.decode[A](text) match {
        case Right(a)    => Future.successful(a)
        case Left(error) => Future.failed(ResponseError(error, response))
      }
    }

  private def decodeStatusDependantResponse[A](response: Response, decoder: StatusDependantDecoder[A]): Future[A] =
    response.text().flatMap { text =>
      decoder.decode(text, response.status) match {
        case Right(a)    => Future.successful(a)
        case Left(error) => Future.failed(ResponseError(error, response))
      }
    }

  private def acceptJson(b: FetchEventStreamBuilder): FetchEventStreamBuilder =
    b.updateHeaders(_.updated("accept", "application/json"))

  def decode[A](implicit decoder: Decoder[A], ec: ExecutionContext): EventStream[FetchResponse[A]] =
    acceptJson(underlying).build(decodeResponse[A](_))

  def decodeEither[NonOkay, Okay](implicit
    decodeNonOkay: Decoder[NonOkay],
    decodeOkay: Decoder[Okay],
    ec: ExecutionContext
  ): EventStream[FetchResponse[Either[NonOkay, Okay]]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse[Okay](response).map(Right(_))
      } else {
        decodeResponse[NonOkay](response).map(Left(_))
      }
    }

  def decodeOkayOr[NonOkay, Okay](
    decodeNonOkay: StatusDependantDecoderBuilder[NonOkay] => StatusDependantDecoderBuilder[NonOkay],
  )(implicit
    decodeOkay: Decoder[Okay],
    ec: ExecutionContext
  ): EventStream[FetchResponse[Either[NonOkay, Okay]]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse[Okay](response).map(Right(_))
      } else {
        decodeStatusDependantResponse[NonOkay](response, decodeNonOkay(new StatusDependantDecoderBuilder[NonOkay]).build).map(Left(_))
      }
    }

  def decodeOkay[Okay](implicit decodeOkay: Decoder[Okay], ec: ExecutionContext): EventStream[FetchResponse[Okay]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse(response)
      } else {
        Future.failed(new NonOkayResponse(response))
      }
    }

}

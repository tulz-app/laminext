package io.laminext.fetch
package circe

import com.raquo.laminar.api.L._
import io.circe._
import io.circe.parser
import org.scalajs.dom.experimental.Response

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits._

class FetchEventStreamBuilderCirceOps(underlying: FetchEventStreamBuilder) {

  private def decodeResponse[A](response: Response)(implicit decoder: Decoder[A]): Future[A] =
    response.text().flatMap { text =>
      parser.decode[A](text) match {
        case Right(a)    => Future.successful(a)
        case Left(error) => Future.failed(ResponseError(error, response))
      }
    }

  private def acceptJson(b: FetchEventStreamBuilder): FetchEventStreamBuilder =
    b.updateHeaders(_.updated("accept", "application/json"))

  def decode[A](implicit decoder: Decoder[A]): EventStream[FetchResponse[A]] =
    acceptJson(underlying).build(decodeResponse[A](_))

  def decodeEither[NonOkay, Okay](implicit
    decodeNonOkay: Decoder[NonOkay],
    decodeOkay: Decoder[Okay]
  ): EventStream[FetchResponse[Either[NonOkay, Okay]]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse[Okay](response).map(Right(_))
      } else {
        decodeResponse[NonOkay](response).map(Left(_))
      }
    }

  def decodeOkay[Okay](implicit decodeOkay: Decoder[Okay]): EventStream[FetchResponse[Okay]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse(response)
      } else {
        Future.failed(new NonOkayResponse(response))
      }
    }

}

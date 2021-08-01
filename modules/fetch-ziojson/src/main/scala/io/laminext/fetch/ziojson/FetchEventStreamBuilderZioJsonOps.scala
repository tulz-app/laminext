package io.laminext.fetch
package ziojson

import com.raquo.laminar.api.L._
import org.scalajs.dom.experimental.Response
import zio.json.JsonDecoder

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits._
import scala.util.control.NoStackTrace

class FetchEventStreamBuilderZioJsonOps(underlying: FetchEventStreamBuilder) {

  private def decodeResponse[A](response: Response)(implicit decoder: JsonDecoder[A]): Future[A] =
    response.text().flatMap { text =>
      decoder.decodeJson(text) match {
        case Right(a)    => Future.successful(a)
        case Left(error) => Future.failed(ResponseError(new Exception(error) with NoStackTrace, response))
      }
    }

  private def acceptJson(b: FetchEventStreamBuilder): FetchEventStreamBuilder =
    b.updateHeaders(_.updated("accept", "application/json"))

  def decode[A](implicit decoder: JsonDecoder[A]): EventStream[FetchResponse[A]] =
    acceptJson(underlying).build(decodeResponse[A](_))

  def decodeEither[NonOkay, Okay](implicit
    decodeNonOkay: JsonDecoder[NonOkay],
    decodeOkay: JsonDecoder[Okay]
  ): EventStream[FetchResponse[Either[NonOkay, Okay]]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse[Okay](response).map(Right(_))
      } else {
        decodeResponse[NonOkay](response).map(Left(_))
      }
    }

  def decodeOkay[Okay](implicit decodeOkay: JsonDecoder[Okay]): EventStream[FetchResponse[Okay]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse(response)
      } else {
        Future.failed(new NonOkayResponse(response))
      }
    }

}

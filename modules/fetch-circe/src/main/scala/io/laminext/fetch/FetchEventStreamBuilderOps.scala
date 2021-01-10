package io.laminext.fetch

import com.raquo.airstream.eventstream.EventStream
import io.circe.Decoder
import org.scalajs.dom.experimental.Response

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits._

class FetchEventStreamBuilderOps(b: FetchEventStreamBuilder) {

  private def decodeResponse[A](response: Response)(implicit decoder: Decoder[A]) =
    response
      .text().flatMap { string =>
        io.circe.parser.decode[A](string) match {
          case Left(error)  => Future.failed(error)
          case Right(value) => Future.successful(value)
        }
      }

  private def acceptJson(b: FetchEventStreamBuilder): FetchEventStreamBuilder =
    b.updateHeaders(_.updated("accept", "application/json"))

  implicit def decode[A](implicit decoder: Decoder[A]): EventStream[FetchResponse[A]] = acceptJson(b).build(decodeResponse[A](_))

  implicit def decodeEither[Err, Resp](implicit decoderErr: Decoder[Err], decoderResp: Decoder[Resp]): EventStream[FetchResponse[Either[Err, Resp]]] =
    acceptJson(b).build { response =>
      if (response.ok) {
        decodeResponse[Resp](response).map(Right(_))
      } else {
        decodeResponse[Err](response).map(Left(_))
      }
    }

  implicit def decodeOkay[A](implicit decoder: Decoder[A]): EventStream[FetchResponse[A]] =
    acceptJson(b).build { response =>
      if (response.ok) {
        decodeResponse(response)
      } else {
        Future.failed(new NonOkayResponse(response))
      }
    }

}

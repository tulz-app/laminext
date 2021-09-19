package io.laminext.fetch
package upickle

import _root_.upickle.default._
import com.raquo.laminar.api.L._
import org.scalajs.dom.experimental.Response
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.Thenable.Implicits._
import scala.util.Failure
import scala.util.Success
import scala.util.Try

class FetchEventStreamBuilderUpickleOps(underlying: FetchEventStreamBuilder) {

  private def decodeResponse[A](response: Response)(implicit decoder: Reader[A]): Future[A] =
    response.text().flatMap { text =>
      Try(read[A](text)) match {
        case Success(a)     => Future.successful(a)
        case Failure(error) => Future.failed(ResponseError(error, response))
      }
    }

  private def acceptJson(b: FetchEventStreamBuilder): FetchEventStreamBuilder =
    b.updateHeaders(_.updated("accept", "application/json"))

  def decode[A](implicit decoder: Reader[A]): EventStream[FetchResponse[A]] =
    acceptJson(underlying).build(decodeResponse[A](_))

  def decodeEither[NonOkay, Okay](implicit
    decodeNonOkay: Reader[NonOkay],
    decodeOkay: Reader[Okay]
  ): EventStream[FetchResponse[Either[NonOkay, Okay]]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse[Okay](response).map(Right(_))
      } else {
        decodeResponse[NonOkay](response).map(Left(_))
      }
    }

  def decodeOkay[Okay](implicit decodeOkay: Reader[Okay]): EventStream[FetchResponse[Okay]] =
    acceptJson(underlying).build { response =>
      if (response.ok) {
        decodeResponse(response)(decodeOkay)
      } else {
        Future.failed(new NonOkayResponse(response))
      }
    }

}

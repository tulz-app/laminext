package io.laminext.fetch

import com.raquo.laminar.api.L._
import org.scalajs.dom.Fetch.fetch
import org.scalajs.dom.AbortController
import org.scalajs.dom.BodyInit
import org.scalajs.dom.HttpMethod
import org.scalajs.dom.ReferrerPolicy
import org.scalajs.dom.RequestCache
import org.scalajs.dom.RequestCredentials
import org.scalajs.dom.RequestInit
import org.scalajs.dom.RequestMode
import org.scalajs.dom.RequestRedirect
import org.scalajs.dom.Response
import scala.concurrent.duration.FiniteDuration
import scala.scalajs.js
import scala.scalajs.js.timers.clearTimeout
import scala.scalajs.js.timers.setTimeout
import scala.scalajs.js.timers.SetTimeoutHandle
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import com.raquo.airstream.custom.CustomSource
import com.raquo.airstream.custom.CustomStreamSource

object FetchEventStream {

  def apply[A](
    url: String,
    method: HttpMethod,
    headers: js.UndefOr[Map[String, String]],
    body: js.UndefOr[BodyInit],
    referrer: js.UndefOr[String],
    referrerPolicy: js.UndefOr[ReferrerPolicy],
    mode: js.UndefOr[RequestMode],
    credentials: js.UndefOr[RequestCredentials],
    cache: js.UndefOr[RequestCache],
    redirect: js.UndefOr[RequestRedirect],
    integrity: js.UndefOr[String],
    keepalive: js.UndefOr[Boolean],
    timeout: js.UndefOr[FiniteDuration],
    extract: Response => Future[A],
  ): EventStream[FetchResponse[A]] = {
    CustomStreamSource[FetchResponse[A]]((fireValue, fireError, _, _) => {
      val abortController                             = new AbortController()
      var timeoutHandle: js.UndefOr[SetTimeoutHandle] = js.undefined

      def handleError(error: Throwable): Unit = {
        timeoutHandle.foreach(clearTimeout)
        timeoutHandle = js.undefined
        fireError {
          error match {
            case e: FetchException => e
            case other             => FetchError(other)
          }
        }
      }

      def sendRequest(): Future[Response] = {
        val init = js.Object().asInstanceOf[RequestInit]
        init.method = method
        init.headers = headers.map { headers =>
          val dict = js.Object().asInstanceOf[js.Dictionary[String]]
          headers.foreach { case (name, value) =>
            dict(name) = value
          }
          dict
        }
        init.body = body
        init.referrer = referrer
        init.referrerPolicy = referrerPolicy
        init.mode = mode
        init.credentials = credentials
        init.cache = cache
        init.redirect = redirect
        init.integrity = integrity
        init.keepalive = keepalive
        init.signal = abortController.signal
        fetch(url, init).toFuture
      }

      CustomSource.Config(
        onStart = () => {
          val response = sendRequest()

          timeout.foreach { timeout =>
            timeoutHandle = setTimeout(timeout) {
              timeoutHandle = js.undefined
              abortController.abort()
              fireError(FetchTimeout(timeout))
            }
          }

          response.onComplete { result =>
            result.fold[Unit](
              handleError,
              response => {
                timeoutHandle.foreach(clearTimeout)
                timeoutHandle = js.undefined
                extract(response).onComplete { extracted =>
                  extracted.fold[Unit](
                    handleError,
                    extracted => {
                      fireValue(
                        FetchResponse[A](
                          ok = response.ok,
                          status = response.status,
                          statusText = response.statusText,
                          headers = response.headers,
                          `type` = response.`type`,
                          data = extracted,
                          url = response.url
                        )
                      )
                    }
                  )
                }
              }
            )
          }
        },
        onStop = () => {
          abortController.abort()
        }
      )
    })
  }

}

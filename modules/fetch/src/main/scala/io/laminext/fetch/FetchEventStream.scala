package io.laminext.fetch

import com.raquo.laminar.api.L._
import org.scalajs.dom.Fetch.fetch
import org.scalajs.dom.AbortController
import org.scalajs.dom.AbortSignal
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
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.Promise
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
  )(implicit ec: ExecutionContext): EventStream[FetchResponse[A]] = {

    val abortController = new AbortController()

    EventStream.fromCustomSource[FetchResponse[A]](
      start = (fireValue, fireError, _, _) => {
        val response = future(
          url,
          method,
          headers,
          body,
          referrer,
          referrerPolicy,
          mode,
          credentials,
          cache,
          redirect,
          integrity,
          keepalive,
          timeout,
          extract,
          abortController,
        )

        response.onComplete { result =>
          result.fold[Unit](fireError, fireValue)
        }
      },
      stop = { _ =>
        abortController.abort()
      }
    )
  }

  def future[A](
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
    abortController: AbortController = new AbortController()
  )(implicit ec: ExecutionContext): Future[FetchResponse[A]] = {
    var timeoutHandle: js.UndefOr[SetTimeoutHandle] = js.undefined
    val promise                                     = Promise[FetchResponse[A]]()

    def handleError(error: Throwable): Unit = {
      timeoutHandle.foreach(clearTimeout)
      timeoutHandle = js.undefined
      error match {
        case e: FetchException => promise.failure(e)
        case other             => promise.failure(FetchError(other))
      }
    }

    val response = sendRequest(
      url,
      method,
      headers,
      body,
      referrer,
      referrerPolicy,
      mode,
      credentials,
      cache,
      redirect,
      integrity,
      keepalive,
      abortController.signal,
    )

    timeout.foreach { timeout =>
      timeoutHandle = setTimeout(timeout) {
        timeoutHandle = js.undefined
        abortController.abort()
        promise.failure(FetchTimeout(timeout))
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
                promise.success(
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

    promise.future
  }

  private def sendRequest(
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
    abortSignal: js.UndefOr[AbortSignal],
  ): Future[Response] = {
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
    abortSignal.foreach { abortSignal =>
      init.signal = abortSignal
    }
    fetch(url, init).toFuture
  }

}

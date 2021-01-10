package io.laminext.fetch

import com.raquo.airstream.eventstream.EventStream
import org.scalajs.dom
import org.scalajs.dom.experimental.HttpMethod
import org.scalajs.dom.experimental.ReadableStream
import org.scalajs.dom.experimental.ReferrerPolicy
import org.scalajs.dom.experimental.RequestCache
import org.scalajs.dom.experimental.RequestCredentials
import org.scalajs.dom.experimental.RequestMode
import org.scalajs.dom.experimental.RequestRedirect
import org.scalajs.dom.experimental.Response

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.typedarray.ArrayBuffer
import scala.scalajs.js.typedarray.Uint8Array

object Fetch {

  @inline def apply(
    method: HttpMethod,
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
    body: RequestBody = RequestBody.noBody,
  ): FetchEventStreamBuilder =
    new FetchEventStreamBuilder(
      _url = url,
      _method = method,
      _headers = headers,
      _body = body
    )

  @inline def get(
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.GET,
      headers = headers,
    )

  @inline def post(
    url: String,
    body: RequestBody = RequestBody.noBody,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.POST,
      headers = headers,
      body = body
    )

  @inline def put(
    url: String,
    body: RequestBody = RequestBody.noBody,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.PUT,
      headers = headers,
      body = body
    )

  @inline def patch(
    url: String,
    body: RequestBody = RequestBody.noBody,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.PATCH,
      headers = headers,
      body = body
    )

  @inline def delete(
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.DELETE,
      headers = headers,
    )

  @inline def query(
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.QUERY,
      headers = headers,
    )

  @inline def head(
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.HEAD,
      headers = headers,
    )

  @inline def options(
    url: String,
    headers: js.UndefOr[Map[String, String]] = js.undefined,
  ): FetchEventStreamBuilder =
    apply(
      url = url,
      method = HttpMethod.OPTIONS,
      headers = headers,
    )

}

final class FetchEventStreamBuilder(
  private var _url: String,
  private var _method: HttpMethod,
  private var _headers: js.UndefOr[Map[String, String]] = js.undefined,
  private var _body: RequestBody = RequestBody.noBody,
  private var _referrer: js.UndefOr[String] = js.undefined,
  private var _referrerPolicy: js.UndefOr[ReferrerPolicy] = js.undefined,
  private var _mode: js.UndefOr[RequestMode] = js.undefined,
  private var _credentials: js.UndefOr[RequestCredentials] = js.undefined,
  private var _cache: js.UndefOr[RequestCache] = js.undefined,
  private var _redirect: js.UndefOr[RequestRedirect] = js.undefined,
  private var _integrity: js.UndefOr[String] = js.undefined,
  private var _keepalive: js.UndefOr[Boolean] = js.undefined,
  private var _timeout: js.UndefOr[FiniteDuration] = js.undefined,
) {

  @inline def build[A](
    extract: Response => Future[A]
  ): EventStream[FetchResponse[A]] =
    FetchEventStream(
      url = _url,
      method = _method,
      headers = _headers,
      body = _body,
      referrer = _referrer,
      referrerPolicy = _referrerPolicy,
      mode = _mode,
      credentials = _credentials,
      cache = _cache,
      redirect = _redirect,
      integrity = _integrity,
      keepalive = _keepalive,
      timeout = _timeout,
      extract = extract
    )

  @inline def raw: EventStream[FetchResponse[Response]] = build(response => Future.successful(response))

  @inline def text: EventStream[FetchResponse[String]] = build(_.text().toFuture)

  @inline def json: EventStream[FetchResponse[js.Any]] = build(_.json().toFuture)

  @inline def blob: EventStream[FetchResponse[dom.Blob]] = build(_.blob().toFuture)

  @inline def arrayBuffer: EventStream[FetchResponse[ArrayBuffer]] = build(_.arrayBuffer().toFuture)

  def headers(
    headers: js.UndefOr[Map[String, String]] = this._headers,
  ): FetchEventStreamBuilder = {
    this._headers = headers
    this
  }

  def body(
    body: RequestBody,
  ): FetchEventStreamBuilder = {
    this._body = body
    this
  }

  def referrer(
    referrer: js.UndefOr[String]
  ): FetchEventStreamBuilder = {
    this._referrer = referrer
    this
  }

  def referrerPolicy(
    referrerPolicy: js.UndefOr[ReferrerPolicy]
  ): FetchEventStreamBuilder = {
    this._referrerPolicy = referrerPolicy
    this
  }

  def mode(
    mode: js.UndefOr[RequestMode]
  ): FetchEventStreamBuilder = {
    this._mode = mode
    this
  }

  def credentials(
    credentials: js.UndefOr[RequestCredentials]
  ): FetchEventStreamBuilder = {
    this._credentials = credentials
    this
  }

  def cache(
    cache: js.UndefOr[RequestCache]
  ): FetchEventStreamBuilder = {
    this._cache = cache
    this
  }

  def redirect(
    redirect: js.UndefOr[RequestRedirect]
  ): FetchEventStreamBuilder = {
    this._redirect = redirect
    this
  }

  def integrity(
    integrity: js.UndefOr[String]
  ): FetchEventStreamBuilder = {
    this._integrity = integrity
    this
  }

  def keepalive(
    keepalive: js.UndefOr[Boolean]
  ): FetchEventStreamBuilder = {
    this._keepalive = keepalive
    this
  }

  def timeout(
    timeout: js.UndefOr[FiniteDuration]
  ): FetchEventStreamBuilder = {
    this._timeout = timeout
    this
  }

  def configure(
    url: String = this._url,
    method: HttpMethod = this._method,
    headers: js.UndefOr[Map[String, String]] = this._headers,
    body: RequestBody = this._body,
    referrer: js.UndefOr[String] = this._referrer,
    referrerPolicy: js.UndefOr[ReferrerPolicy] = this._referrerPolicy,
    mode: js.UndefOr[RequestMode] = this._mode,
    credentials: js.UndefOr[RequestCredentials] = this._credentials,
    cache: js.UndefOr[RequestCache] = this._cache,
    redirect: js.UndefOr[RequestRedirect] = this._redirect,
    integrity: js.UndefOr[String] = this._integrity,
    keepalive: js.UndefOr[Boolean] = this._keepalive,
    timeout: js.UndefOr[FiniteDuration] = this._timeout
  ): FetchEventStreamBuilder = {
    this._url = url
    this._method = method
    this._headers = headers
    this._body = body
    this._referrer = referrer
    this._referrerPolicy = referrerPolicy
    this._mode = mode
    this._credentials = credentials
    this._cache = cache
    this._redirect = redirect
    this._integrity = integrity
    this._keepalive = keepalive
    this._timeout = timeout
    this
  }

}

## Request body

The request body for the underlying `fetch` call can be one of the following:

* `String`
* `dom.Blob`
* `dom.FormData`
* `dom.crypto.BufferSource`

This is encapsulated in the `ToRequestBody` trait:

```scala
import scala.scalajs.js
import org.scalajs.dom.experimental.BodyInit

trait ToRequestBody {
  def apply(): js.UndefOr[BodyInit]
  def updateHeaders(headers: js.UndefOr[Map[String, String]]): js.UndefOr[Map[String, String]] = headers
}
```

(`BodyInit` is defined as `type BodyInit = Blob | BufferSource | FormData | String`)

(`updateHeaders` can be overridden in your custom `RequestBody`; `fetch-circe` adds a `content-type: application/json; charset=utf-8` header, for example)

There are default implicit conversions from `Blob`, `BufferSource`, `FormData`, and `String` to `RequestBody`, so you
can pass values of those types directly.

Also, you can provide implicit conversions for other data types (see [Circe support](/fetch/circe) for an example).

You can specify the request body when building a `POST`, `PUT` or a `PATCH` request (or when specifying 
the method as a parameter):

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom
import org.scalajs.dom.experimental.HttpMethod

val blob: dom.Blob = ???
val formData: dom.FormData = ???

Fetch.post("https://...", body = blob)
Fetch.put("https://...", body = "A string")
Fetch.patch("https://...", body = formData)

Fetch(HttpMethod.POST, "https://...", body = blob)
```

Next, [Other request settings](/fetch/request-settings).

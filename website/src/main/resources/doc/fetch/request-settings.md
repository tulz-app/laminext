## Other request settings

The `FetchEventStreamBuilder` provides methods for specifying all the settings that the underlying Fetch API supports.

(see [Using Fetch](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch) for more details on these settings)

### Headers 

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").headers(Map("Authorization" -> "Bearer ..."))
```

### Body 

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").body("a string body")
```


### Referrer

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").referrer("...")
```

### Referrer policy

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.ReferrerPolicy

Fetch.get("https://...").referrerPolicy(ReferrerPolicy.`origin-when-cross-origin`)
```

### Request mode

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.RequestMode

Fetch.get("https://...").mode(RequestMode.`same-origin`)
```

### Request credentials

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.RequestCredentials

Fetch.get("https://...").credentials(RequestCredentials.omit)
```

### Cache

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.RequestCache

Fetch.get("https://...").cache(RequestCache.`no-cache`)
```

### Redirect

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.RequestRedirect

Fetch.get("https://...").redirect(RequestRedirect.follow)
```

### Integrity

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").integrity("...")
)
```

### Timeout

```scala
import io.laminext.fetch.Fetch
import scala.concurrent.duration._

Fetch.post("https://...").timeout(10.seconds)
```

Note: the timeout is implemented in the `FetchEventStream` (it's not provided by the Fetch API).

When the timeout occurs, the request is aborted if it has not completed by that time. 

### Configure

Also, there is a `configure` method that allows to customize all the needed settings (as well as override the already
configured ones, like the method, headers or the body) with one call.

## Response

After you've configured the request, you need to specify how you want to handle the [response](/fetch/response).

Next, [Handling the response](/fetch/response).

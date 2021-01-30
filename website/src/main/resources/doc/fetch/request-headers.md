## Request headers

Each of the "http-method" functions in the `Fetch` object also accepts an optional `headers` parameter:

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...", headers = Map("Authorization" -> "Bearer ..."))
```

Another way is to specify the headers afterwards, using the `headers` method of the `FetchEventStreamBuilder`:

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").headers(Map("authorization" -> "Bearer ..."))
```

Or to use the `updateHeaders` method:

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").updateHeaders(_.updated("authorization", "Bearer ..."))
```

There's also a `addHeaders` method:

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").addHeaders("authorization" -> "Bearer ...", "x-another-header" -> "another-header-value")
```

And `addAuthorizationHeader` method:

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...").addAuthorizationHeader("Bearer ...")
```


Next, [Request body](/fetch/request-body).

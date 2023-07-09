## Request method

The `Fetch` object is a starting point to build the request. 

It provides functions to specify the HTTP method you want to use. 

These functions return a `FetchEventStreamBuilder` which allows to specify the rest of the request settings.

Note that the `FetchEventStreamBuilder` is not immutable. It can be safely re-used, but calls to all configuration 
methods **do** modify the instance.

```scala
import io.laminext.fetch.Fetch

Fetch.get("https://...")
Fetch.post("https://...", body = ???)
Fetch.put("https://...", body = ???)
Fetch.patch("https://...", body = ???)
Fetch.delete("https://...")
Fetch.query("https://...")
Fetch.options("https://...")
```

You can also specify the method as a parameter:

```scala
import io.laminext.fetch.Fetch
import org.scalajs.dom.HttpMethod

Fetch(HttpMethod.GET, "https://...")
```

Each of these functions accepts an optional `headers` parameter (see [Request headers](/fetch/request-headers)).

`post`, `put` and `path` functions accept an optional `body` parameter (see [Request body](/fetch/request-body)).

Next, [Request headers](/fetch/request-headers).

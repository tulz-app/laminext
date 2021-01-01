# Util

## `UrlString`

```scala
import io.laminext.util.UrlString
val url = "https://google.com/path?q=laminar"
val UrlString(location) = url
// or 
url match {
  case UrlString(location) => // ... 
}
// location is a parsed Location 
```

## `HumanReadableSize`

See [example](/util/example-human-readable-size).

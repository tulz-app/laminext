```scala
libraryDependencies += "io.laminext" %%% "validation-cats" % "{{laminextVersion}}"
```

This module provides the the `&` and `|` combinators. These are non fail-fast and will report 
all validation errors (combined using the corresponding `Semigroup` instance).

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.validation.cats._

input().validated(
  V.nonBlank("Must not be blank!") &
    (V.custom("Must be upper-case!")(string => string.toUpperCase == string) |
      V.custom("Must be lower-case!")(string => string.toLowerCase == string))
)
```

See [example](/validation/example-validation-cats).

### Deprecated

Tailwind module is deprecated and will be removed in the next version.

There will be no replacement for the `buttons` utilities, other functionality will be made agnostic 
of the styling framework and will be moved to the `ui` module.

```scala
libraryDependencies += "io.laminext" %%% "tailwind" % "{{laminextVersion}}"
```

```scala
import io.laminext.syntax.tailwind._
```

This module provides a set of utilities and components for building UIs using [Tailwind CSS](https://tailwindcss.com/).

* [buttons](/tailwind/buttons)
* [transition](/tailwind/transition)


All components and utilities in the `laminext-tailwind` module use the current theme.

The current theme is available via `io.laminext.tailwind.theme.Theme.current`.

By default, the current theme is empty – it contains no classes.

To set the theme, there's a `Theme.setTheme` method, which should be called at the start of the application.

```scala
import io.laminext.tailwind.theme.Theme

val myTheme = Theme.empty.customize(/* ... */)
Theme.setTheme(myTheme)
```

There is the `tailwind-default-theme` module that provides a default theme that you can use or derive your own theme
from.

```scala
libraryDependencies += "io.laminext" %%% "tailwind-default-theme" % "{{laminextVersion}}"
```

```scala
import io.laminext.tailwind.theme.Theme
import io.laminext.tailwind.theme.DefaultTheme

Theme.setTheme(DefaultTheme.theme)
```

## Components

Theme contains the configuration for the following components:

* `animation` – `io.laminext.tailwind.theme.Animation`
* `button` – `io.laminext.tailwind.theme.Button`
* `buttonGroup` – `io.laminext.tailwind.theme.BaseAndCustom`
* `card` – `io.laminext.tailwind.theme.Card`
* `transition` – `io.laminext.tailwind.theme.Transition`
* `modal` – `io.laminext.tailwind.theme.Modal`
* `progressBar` – `io.laminext.tailwind.theme.ProgressBar`
* `fileInput` – `io.laminext.tailwind.theme.FileInput`

## Customization

Theme class has a `customize` method that allows to customize the theme.

All parameters are optional (default to `identity`).


```scala
import io.laminext.tailwind.theme.Theme
import io.laminext.tailwind.theme.DefaultTheme

val myTheme = DefaultTheme.theme.customize(
  animation = _.customize(_.custom("my-additional-class")),
  button = _.customize(
    common = _.reset("my-class"),
    disabled = _.custom("my-additional-class"),
    single = _.custom("my-additional-class"),
    size = _.customize(
      sm = _.custom("my-additional-class")
    ),
    group = _.custom("my-additional-class"),
    color = _.customize(/* colors customizations */)
  ),
  buttonGroup = _.custom("my-additional-class"),
  card = _.customize(
    wrap = _.custom("my-additional-class"),
    header = _.custom("my-additional-class"),
    body = _.custom("my-additional-class"),
    footer = _.custom("my-additional-class"),
    title = _.custom("my-additional-class")
  ),
  transition = _.customize(
    default = _.customize(/* transition customization */),
    scale = _.customize(/* transition customization */),
    opacity = _.customize(/* transition customization */),
    opacityAndScale = _.customize(/* transition customization */),
    resize = _.customize(/* transition customization */)
  ),
  modal = _.customize(
    container = _.custom("my-additional-class"),
    overlayTransition = _.customize(/* transition customization */),
    contentWrapTransition = _.customize(/* transition customization */),
    contentWrapInner = _.custom("my-additional-class")
  ),
  progressBar = _.customize(
    wrap = _.custom("my-additional-class"),
    progress = _.custom("my-additional-class")
  ),
  fileInput = _.customize(
    selecting = _.custom("my-additional-class"),
    invalid = _.custom("my-additional-class"),
    ready = _.custom("my-additional-class")
  )
)
```

## BaseAncCustom

Many of the configuration options are defined with a `BaseAndCustom` class. It's a simple class
that contains two fields:

```scala
case class BaseAndCustom(
  base: String,
  custom: String = ""
)
```

When used by a component, both base and custom are combined to get a CSS class to be used.

`BaseAndCustom` provides a `custom(custom: String)` method that allows to set the `custom` field. 

There's also a `reset` method that allows to discard the current values start from scratch (by setting a new 
base value and setting `custom` to "").

This is intended to make customization of a theme easier (see the snippet above).

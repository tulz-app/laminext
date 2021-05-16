# Modal

```scala
def modal(
  content: Signal[Option[ModalContent]],
  styling: Modal.Styling
)
```

In order to use a modal, first you need to have a signal that will contain the modal content
(`Signal[Option[ModalContent]]`):

```scala
final case class ModalContent(
  content: Element,
  closeObserver: Option[Observer[Unit]]
)

```

Next, you need to define a "styling" for the modal (which controls the look and feel of the modal, as well as the
transitions). Example (using TailwindCSS):

```scala
val modalStyling: Modal.Styling = Modal.Styling(
  container = cls("fixed inset-0 z-50"),
  overlayTransition = TransitionConfig(
    nonHidden = "hidden md:block md:absolute md:inset-0 bg-gray-900",
    showing = "bg-opacity-75",
    inTransition = "hidden md:absolute md:transition-opacity motion-reduce:transition-none",
    enter = "duration-100 ease-out",
    enterFrom = "bg-opacity-0",
    enterTo = "bg-opacity-75",
    leave = "duration-150 ease-in",
    leaveFrom = "bg-opacity-75",
    leaveTo = "bg-opacity-0"
  ),
  contentWrapTransition = TransitionConfig(
    nonHidden =
      "w-full h-screen pb-20 overflow-y-scroll bg-white md:absolute md:top-1/3 left-1/2 md:-translate-y-1/3 md:-translate-x-1/2 md:max-w-4xl md:h-auto md:pb-0 md:rounded-lg md:shadow-xl",
    showing = "transform",
    inTransition = "transform transition-all motion-reduce:transition-none motion-reduce:transform-none",
    enter = "duration-100 ease-out",
    enterFrom = "scale-75",
    enterTo = "scale-100",
    leave = "duration-150 ease-in",
    leaveFrom = "scale-100",
    leaveTo = "scale-75"
  ),
  contentWrapInner = cls("md:mx-auto")
)
```

(`tailwind` module provides a styling using TailwindCSS: `TailwindModal.styling`)

Then you mount a modal container in the DOM:

```scala
import com.raquo.laminar.api.L._
import io.laminext.ui._

val modalStyling: Modal.Styling = ???
val modalContent: Var[Option[ModalContent]] = ??? 

div(
  modal(modalContent.signal, modalStyling),
)
```

Whenever the value contained in the content signal is defined (`Some(...)`), the modal will pop up. When
signal contains `None`, the modal will close.

### Closing modal on click outside

`ModalContent` can provide a `closeObserver`. If it's provided,
it will be called when the user clicks outside the modal.

### Scrollbars

In some browser/OSes, the scrollbars affect the width of the content area when they are visible.

There is the `Modal.initialize` function:

```scala
import io.laminext.syntax.tailwind._

Modal.initialize()
```

which generates a `--modal-no-scroll` class based on the width of the scrollbars. This class is added to the `<html>`
element whenever a modal is active.

In order for this to work, though, you need your site to always have the scrollbar visible.

[Example](/tailwind/example-modal).


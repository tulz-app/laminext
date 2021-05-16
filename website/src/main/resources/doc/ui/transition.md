# Transition

Animate transitions between two states of an element (most commonly visible and hidden).

### Transition configuration

A transition is defined by a configuration:

```scala
final case class TransitionConfig(
  hidden: Seq[String],
  nonHidden: Seq[String],
  showing: Seq[String],
  inTransition: Seq[String],
  enter: Seq[String],
  enterFrom: Seq[String],
  enterTo: Seq[String],
  leave: Seq[String],
  leaveFrom: Seq[String],
  leaveTo: Seq[String],
  onEnterFrom: dom.html.Element => Unit,
  onEnterTo: dom.html.Element => Unit,
  onLeaveFrom: dom.html.Element => Unit,
  onLeaveTo: dom.html.Element => Unit,
  onReset: (dom.html.Element, Boolean) => Unit
)
```

The transition configuration is built using CSS classes. Example (using TailwindCSS):

```scala
val myTransitionConfig = TransitionConfig(
  hidden = "hidden",
  inTransition = "transform transition-all motion-reduce:transition-none",
  enter = "duration-1000 ease-out",
  enterFrom = "opacity-0 scale-75",
  enterTo = "opacity-100 scale-100",
  leave = "duration-1000 ease-in",
  leaveFrom = "opacity-100 scale-100",
  leaveTo = "opacity-0 scale-75"
)
```

#### Config options

* `hidden` – classes to be active to an element when it's hidden (`show` signal emits `false`)
* `nonHidden` – ... when the element is showing or in transition 
* `showing` – ... when the element is showing (the transition has ended)
* `inTransition` – ... when the element is in transition
* `enter`  – ... when the element is "entering" (transitioning from `hidden` to `showing` state)
* `enterFrom` – staring classes of the "enter" transition
* `enterTo` – target classes of the "enter" transition
* `leave` – classes to be added when the element is "leaving" (transitioning from `showing` to `hidden` state)
* `leaveFrom` – staring classes of the "leave" transition
* `leaveTo` – target classes of the "leave" transition
* `onEnterFrom` – an optional callback that gets called before setting the `enterFrom` classes (can be used for setting the additional
  element settings for the `enterFrom` state) 
* `onEnterTo` – an optional callback that gets called before setting the `enterTo` classes (can be used for setting the additional
  element settings for the `enterTo` state)
* `onLeaveFrom` – an optional callback that gets called before setting the `leaveFrom` classes (can be used for setting the additional
  element settings for the `leaveFrom` state)
* `onLeaveTo` – an optional callback that gets called before setting the `leaveTo` classes (can be used for setting the additional
  element settings for the `leaveFrom` state)
* `onReset` – an optional callback to reset the element

The callbacks can be used when you want to animate more complicated transitions of your element. This site uses the callbacks to transition
between code-collapsed and code-expanded states:

```scala
val collapseTransition = TailwindTransition.resize.customize(
  hidden = _ :+ "max-h-32",
  enterFrom = _ :+ "max-h-32",
  enterTo = _ => Seq.empty,
  leaveFrom = _ => Seq.empty,
  leaveTo = _ :+ "max-h-32",
  onEnterFrom = el => {
    el.style.maxHeight = null
  },
  onEnterTo = el => {
    el.style.maxHeight = el.scrollHeight + "px"
  },
  onLeaveFrom = el => {
    el.style.maxHeight = el.scrollHeight + "px"
  },
  onLeaveTo = el => {
    el.style.maxHeight = null
  },
  onReset = (el, _) => {
    el.style.maxHeight = null
  }
)
```

### Adding transition to an element

To add a transition to an element, use the `addTransition` function (it returns a `Modifier`).

```scala
def addTransition(
  show: Signal[Boolean],
  config: TransitionConfig,
  observer: Observer[Boolean] = Observer.empty
)
```

```scala
val showMe: Signal[Boolean] = ???
div(
  addTransition(
    show = showMe,
    config = myTransitionConfig
  )
)
```

This modifier will control the visibility of an element using the `show` signal. 

`tailwind` module provides a few transition configurations: [tailwind/transition](/tailwind/transition)

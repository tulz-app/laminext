```scala
val s1: Signal[Boolean] = ???
val s2: Signal[Boolean] = ???

val sOr: Signal[Boolean]  = s1 || s2
val sAnd: Signal[Boolean] = s1 && s2
val sNot: Signal[Boolean] = s1.not
val sNeg: Signal[Boolean] = !s1

val sOrBoolean: Signal[Boolean]  = s1 || true
val sAndBoolean: Signal[Boolean] = s1 && false

div(
  s1.childWhenTrue {
    span("s1 is true")
  },
  s1.childWhenFalse {
    span("s1 is false")
  },
  s1.doWhenTrue { () =>
    dom.console.log("s1 is true")    
  },
  s1.doWhenFalse { () =>
    dom.console.log("s1 is false")
  }
)
```


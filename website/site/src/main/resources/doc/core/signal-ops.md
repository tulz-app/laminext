```scala
val s: Signal[String] = ???
val transitions: Signal[(Option[String], String)] = s.transitions
```

See [example](/core/signal-transitions).

---

```scala
val s: Signal[String] = ???

div(
  // alias for -->, more IntelliJ friendly
  s.bind { string => 
    dom.console.log(string)  
  },

  s.bindCollect {
    case s if s.length < 5 =>
      dom.console.log("it's shorter than 5!")
  }  
)
```

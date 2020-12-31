```scala
val stream: EventStream[Option[String]] = ???

val defined:     EventStream[String]         = stream.collectDefined
val collected:   EventStream[Int]            = stream.optionCollect {
                                                 case s if s.length < 5 => s.length 
                                               }
val isDefined:   EventStream[Boolean]        = stream.isDefined
val isEmpty:     EventStream[Boolean]        = stream.isEmpty
val contains:    EventStream[Boolean]        = stream.optionContains("abc")
val flatMapped:  EventStream[Option[String]] = stream.optionFlatMap(s => Some(s).map(_.trim).filterNot(_.isEmpty))
val withDefault: EventStream[String]         = stream.withDefault("DEFAULT")
```

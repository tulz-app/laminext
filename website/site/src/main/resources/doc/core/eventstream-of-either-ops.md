```scala
val stream: EventStream[Either[String, Double]] = ???

val isRight:  EventStream[Boolean] = stream.isRight
val isLeft:   EventStream[Boolean] = stream.isLeft
val rights:   EventStream[Double]  = stream.collectRights
val lefts:    EventStream[String]  = stream.collectLefts

val rightCollected: EventStream[Either[String, Long]] = stream.rightCollect {
                                                          case d if d > 10 => Math.round(d)
                                                        }

val leftCollected: EventStream[Either[Int, Double]]   = stream.leftCollect {
                                                          case s => s.length
                                                        }

def makeStreamFromRight(d: Double): EventStream[Either[String, SomeData]] = ???
def makeStreamFromLeft(s: String): EventStream[Either[SomeData, Double]] = ???

val rightFlatMapped: EventStream[Either[String, SomeData]] = stream.rightFlatMap(makeStreamFromRight) // Left's in the parent stream are left as is 
val leftFlatMapped:  EventStream[Either[SomeData, Double]] = stream.leftFlatMap(makeStreamFromLeft)  // Right's in the parent stream are left as is 

val rightMapped: EventStream[Either[String, Long]]   = stream.rightMap(d => Math.round(d))
val leftMapped:  EventStream[Either[Int, Double]]    = stream.leftMap(s => s.length)
val swapped:     EventStream[Either[Double, String]] = stream.eitherSwap
```

```scala
val s1: Signal[Either[String, Int]] = ???

val left: Signal[Boolean] = s1.isLeft

val right: Signal[Boolean] = s1.isRight

val option: Signal[Option[Int]] = s1.eitherToOption

val swapped: Signal[Either[Int, String]] = s1.eitherSwap

val leftMapped: Signal[Either[Int, Int]] s1.leftMap(_.length)

val rightMapped: Signal[Either[String, Boolean]] s1.leftMap(_ > 5)

val folded: Signal[Int] = s1.eitherFold(_.length < 5, _ > 5)
```


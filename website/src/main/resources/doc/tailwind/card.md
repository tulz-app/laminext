There are five extension methods to build cards. See the [example](/tailwind/example-card)

* `.card.wrap`
* `.card.header`
* `.card.body`
* `.card.footer`
* `.card.title`

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._

div.card.wrap(
  div.card.header(
    div.card.title("header")
  ),
  div.card.body("body"),
  div.card.footer("footer")
)
```

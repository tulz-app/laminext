You can build buttons using the extension methods provided.

Start with a Laminar tag (`button`, `a`, or any other) call the `.btn` extension method 
and provide the button parameters as a chain of subsequent extension method calls.

There are two types of buttons: single and group.

In order to define a group button, call `.group` followed by the button's position in a group:

* `.first`
* `.inner`
* `.last`

You might also wrap the buttons in a "button group container": `div.buttonGroup`.

The following is the same for single and group buttons:

* specify the size: `.xs`, `.sm`, `.md`, `.lg`, `.xl`
* specify the style: `.fill`, `.outline`, `.text`
* specify the color: 
  * `.blue`
  * `.green`
  * `.red`
  * `.yellow`
  * `.indigo`
  * `.purple`
  * `.white`
  * `.black`


See the examples to get a better idea of how it looks like:

* [button colors](/tailwind/example-button-colors)
* [button sizes](/tailwind/example-button-sizes)
* [button group](/tailwind/example-button-group)

## Partially built buttons

You can get a tag at any point of describing a button:

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._

button.btn.group.first.lg.build("partially defined button")
```

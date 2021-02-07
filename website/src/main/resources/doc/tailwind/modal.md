In order to use the `modal` component:

* call `Modal.initialize()` at the start of the application

```scala
import io.laminext.syntax.tailwind._
Modal.initialize()
```

* define a signal that will contain the modal content (`Signal[Option[ModalContent]]`)
* mount a modal container in the DOM:

```scala
import com.raquo.laminar.api.L._
import io.laminext.syntax.tailwind._
import org.scalajs.dom

val myModalContentVar: Var[Option[ModalContent]]
val modalContainer = dom.document.querySelector("#my-modal-container")
render(
  modalContainer,
  TW.modal(myModalContentVar.signal /*, config = myCustomModalConfig */)
)
```

* set the value of the modal content signal to `Some(ModalContent(...))` in order to 
display the modal; set it to `None` to hide the modal.

`ModalContent` is defined as follows:

```scala
import com.raquo.laminar.api.L._
case class ModalContent(
  content: Element,
  closeObserver: Option[Observer[Unit]]
)
```

* `content` will be displayed in the modal
* `closeObserver`, if provided, will be called when the user clicks outside the modal 

See the [example](/tailwind/example-modal).

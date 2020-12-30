package app.tulz.laminext.site.examples.eventproptransformation.ex_eventprop_stream

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object EventPropStreamExample
    extends CodeExample(
      id = "eventprop-stream",
      title = "EventProp Stream",
      description = FileToLiteral("description.md")
    )(() => {
      import app.tulz.laminar.ext._
      import com.raquo.laminar.api.L._
      import org.scalajs.dom

      val aVar = Var("initial")

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )

      div(
        inputElement.valueSignal --> aVar.writer,
        div(
          inputElement
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-gray-300 shadow-sm text-sm leading-4 font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500",
            "click me",
            onClick.stream.sample(aVar.signal).map(_.toUpperCase) --> { s =>
              dom.window.alert(s"You typed: ${s}")
            }
          )
        )
      )
    })

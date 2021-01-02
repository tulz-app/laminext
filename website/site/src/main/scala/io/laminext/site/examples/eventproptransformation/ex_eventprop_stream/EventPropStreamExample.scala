package io.laminext.site.examples.eventproptransformation.ex_eventprop_stream

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object EventPropStreamExample
    extends CodeExample(
      id = "example-eventprop-stream",
      title = "EventProp as Stream",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.all._

      val aVar = Var("initial")

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )

      div(
        cls := "space-y-4",
        inputElement.valueSignal --> aVar.writer,
        div(
          inputElement
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-200 shadow-sm text-sm leading-4 font-medium rounded-md text-blue-700 bg-white hover:bg-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "click me",
            onClick.stream.sample(aVar.signal).map(_.toUpperCase).collect {
              case s if s.trim.nonEmpty => s
            } --> { s =>
              org.scalajs.dom.window.alert(s"You typed: ${s}")
            }
          )
        )
      )
    })

package io.laminext.site.examples.signal.ex_signal_of_option

import io.laminext.site.examples.CodeExample
import com.yurique.embedded.FileAsString

object SignalOfOptionExample
    extends CodeExample(
      id = "example-signal-of-option",
      title = "Signal of Option",
      description = FileAsString("description.md")
    )(() => {
      import io.laminext.syntax.all._
      import com.raquo.laminar.api.L._

      val aVar   = Var(Option.empty[String])
      val signal = aVar.signal

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "new value"
      )
      val updateButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "update signal"
      )
      val removeButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
        "remove value from signal",
        onClick --> { _ =>
          aVar.writer.onNext(Option.empty)
        }
      )

      div(
        updateButton
          .events(onClick).mapTo(
            Option(inputElement.ref.value)
          ) --> aVar.writer,
        cls := "space-y-4",
        div(inputElement),
        div(
          cls := "flex space-x-4",
          updateButton,
          removeButton
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal.map(_.toString)
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.isEmpty:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal.isEmpty.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.isDefined:"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal.isDefined.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.optionMap(_.toUpperCase):"),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal.optionMap(_.toUpperCase).map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code(
            "signal.optionFlatMap(s => Option(s.toUpperCase).filterNot(_.isEmpty)):"
          ),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal
              .optionFlatMap(s => Option(s.toUpperCase).filterNot(_.isEmpty)).map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-4 items-center",
          code("signal.withDefault(\"DEFAULT\"): "),
          code(
            cls := "text-blue-700 font-medium",
            child.text <-- signal.withDefault("DEFAULT")
          )
        )
      )
    })

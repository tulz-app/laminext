package app.tulz.laminext.site.examples.signal.ex_signal_of_option

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.website.macros.FileToLiteral

object SignalOfOptionExample
    extends CodeExample(
      id = "signal-of-option",
      title = "Signal of Option",
      description = FileToLiteral("description.md")
    )(() => {
      import app.tulz.laminar.ext._
      import com.raquo.laminar.api.L._

      val aVar   = Var(Option.empty[String])
      val signal = aVar.signal

      val inputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-indigo-500 focus:border-indigo-500 block w-full sm:text-sm border-gray-300 rounded-md",
        placeholder := "new value"
      )
      val updateButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-gray-300 shadow-sm text-sm leading-4 font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500",
        "update signal"
      )
      val removeButton = button(
        cls := "inline-flex items-center px-3 py-2 border border-gray-300 shadow-sm text-sm leading-4 font-medium rounded-md text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500",
        "remove value from signal",
        onClick --> { _ =>
          aVar.writer.onNext(Option.empty)
        }
      )

      div(
        updateButton.events(onClick).mapTo(Option(inputElement.ref.value)) --> aVar.writer,
        cls := "space-y-1",
        div(
          inputElement
        ),
        div(
        ),
        div(
          cls := "flex space-x-4",
          updateButton,
          removeButton
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal: "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.map(_.toString)
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal.isEmpty: "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.isEmpty.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal.isDefined: "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.isDefined.map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal.optionMap(_.toUpperCase): "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.optionMap(_.toUpperCase).map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal.optionFlatMap(s => Option(s.toUpperCase).filterNot(_.isEmpty)): "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.optionFlatMap(s => Option(s.toUpperCase).filterNot(_.isEmpty)).map(_.toString())
          )
        ),
        div(
          cls := "flex space-x-1 items-center",
          span("signal.withDefault(\"DEFAULT\"): "),
          code(
            cls := "text-blue-600",
            child.text <-- signal.withDefault("DEFAULT")
          )
        )
      )
    })

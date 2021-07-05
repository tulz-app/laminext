package io.laminext.site.examples.observable.ex_observable_switching_bind

import com.raquo.laminar.CollectionCommand
import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

import scala.util.Failure
import scala.util.Success

object ObservableSwitchingBindExample
    extends CodeExample(
      id = "example-add-switching-observer",
      title = "Add switching observer",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._

      val prefixes         = Var(Option.empty[String])
      val observers        = Var(Option.empty[Observer[String]])
      val messages         = new EventBus[String]
      val observerMessages = new EventBus[String]

      val prefixInputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "observer prefix"
      )
      val messageInputElement = input(
        tpe := "text",
        cls := "shadow-sm focus:ring-blue-500 focus:border-blue-500 block w-full sm:text-sm border-blue-300 rounded-md bg-blue-50 text-blue-700 placeholder-blue-400 font-mono",
        placeholder := "message"
      )
      div(
        cls := "space-y-4",
        div(
          prefixInputElement
        ),
        div(
          cls := "space-x-4",
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "change observer",
            thisEvents(onClick)
              .sample(prefixInputElement.value)
              .map(prefix => Option.when(prefix.nonEmpty)(Observer[String](message => observerMessages.emit(s"$prefix – $message")))) --> observers,
            thisEvents(onClick)
              .sample(prefixInputElement.value)
              .map(prefix => Option.when(prefix.nonEmpty)(prefix)) --> prefixes,
          ),
          code(
            cls := "space-x-1",
            "Current prefix: ",
            span(
              child.text <-- prefixes.signal.debugLogEvents().map(_.toString)
            )
          )
        ),
        div(
          messageInputElement
        ),
        div(
          button(
            cls := "inline-flex items-center px-3 py-2 border border-blue-500 shadow-sm tracking-wide font-medium rounded-md text-blue-100 bg-blue-600 hover:bg-blue-500 hover:text-blue-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500",
            "emit message",
            thisEvents(onClick)
              .sample(messageInputElement.value) --> messages
          )
        ),
        /* <focus> */
        messages.events.addOptionalSwitchingObserver(observers),
        /* </focus> */
        div(
          cls := "flex flex-col space-y-4 p-4 max-h-96 overflow-auto bg-gray-900",
          children.command <-- observerMessages.events.map { observerMessage =>
            CollectionCommand.Append(
              div(
                cls := "text-green-400 text-xs",
                code(observerMessage)
              )
            )
          }
        )
      )
    })

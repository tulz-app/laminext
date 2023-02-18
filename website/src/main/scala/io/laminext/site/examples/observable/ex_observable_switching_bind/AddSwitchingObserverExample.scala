package io.laminext.site.examples.observable.ex_observable_switching_bind

import com.yurique.embedded.FileAsString
import io.laminext.site.examples.CodeExample

object AddSwitchingObserverExample
    extends CodeExample(
      id = "example-add-switching-observer",
      title = "Add switching observer",
      description = FileAsString("description.md")
    )(() => {
      import com.raquo.laminar.api.L._
      import io.laminext.syntax.core._

      val prefixes         = Var(Option.empty[String])
      /* <focus> */
      val observers        = Var(Option.empty[Observer[String]])
      /* </focus> */
      val messages         = new EventBus[String]
      val observerMessages = new EventBus[String]

      val prefixInputElement  = input(
        tpe         := "text",
        placeholder := "observer prefix"
      )
      val messageInputElement = input(
        tpe         := "text",
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
            cls := "btn-md-fill-blue",
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
            cls := "btn-md-fill-blue",
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

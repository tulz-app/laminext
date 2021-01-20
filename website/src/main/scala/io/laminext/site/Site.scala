package io.laminext.site

import io.laminext.site.examples.CodeExample
import io.laminext.site.pages.CodeExamplePage
import io.laminext.site.pages.DocumentationPage
import com.yurique.embedded.FileAsString

object Site {

//  private def indexExamplePage(
//    example: CodeExample
//  ): Page = Page("", example.title, CodeExamplePage(example))

  private def examplePage(
    example: CodeExample
  ): Page = Page(example.id, example.title, CodeExamplePage(example))

  private def docPage(
    path: String,
    title: String,
    markdown: String
  ): Page = Page(path, title, DocumentationPage(title, markdown))

  val indexModule: SiteModule =
    SiteModule(
      path = "",
      index = docPage("", "laminext", FileAsString("/doc/index.md"))
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      index = docPage("", "Core", FileAsString("/doc/core/index.md")),
      "Observable" -> Seq(
        docPage("observable", "Observable", FileAsString("/doc/core/observable.md")),
        docPage("observable-of-boolean", "Observable of Boolean", FileAsString("/doc/core/observable-of-boolean.md")),
        docPage("observable-of-option", "Observable of Option", FileAsString("/doc/core/observable-of-option.md")),
        docPage("observable-of-either", "Observable of Either", FileAsString("/doc/core/observable-of-either.md")),
      ),
      "Signal" -> Seq(
        docPage("signal", "Signal", FileAsString("/doc/core/signal.md")),
        docPage("signal-of-boolean", "Signal of Boolean", FileAsString("/doc/core/signal-of-boolean.md")),
        docPage("signal-of-option", "Signal of Option", FileAsString("/doc/core/signal-of-option.md")),
        docPage("signal-of-either", "Signal of Either", FileAsString("/doc/core/signal-of-either.md")),
        docPage("signal-of-option-of-signal", "Signal of Option of Signal", FileAsString("/doc/core/signal-of-option-of-signal.md")),
        docPage("option-of-signal", "Option of Signal", FileAsString("/doc/core/option-of-signal.md")),
        docPage("var-of-boolean", "Var of Boolean", FileAsString("/doc/core/var-of-boolean.md"))
      ),
      "EventStream" -> Seq(
        docPage("eventstream", "EventStream", FileAsString("/doc/core/eventstream.md")),
        docPage("eventstream-of-unit", "EventStream of Unit", FileAsString("/doc/core/eventstream-of-unit.md")),
        docPage("eventstream-of-option", "EventStream of Option", FileAsString("/doc/core/eventstream-of-option.md")),
        docPage("eventstream-of-either", "EventStream of Either", FileAsString("/doc/core/eventstream-of-either.md")),
      ),
      "EventProp" -> Seq(
        docPage("this-event", "thisEvents", FileAsString("/doc/core/this-events.md")),
      ),
      "Input Elements" -> Seq(
        docPage("input-values", "Input Values", FileAsString("/doc/core/input-values.md"))
      ),
      "Misc" -> Seq(
        docPage("helpers", "Small helpers", FileAsString("/doc/core/helpers.md")),
      ),
      "Examples" -> Seq(
        examplePage(examples.signal.ex_signal_transitions.SignalTransitionsExample),
        examplePage(examples.signal.ex_var_of_boolean_toggle.VarOfBooleanToggleExample),
        examplePage(examples.signal.ex_signal_of_boolean_class_switch.SignalOfBooleanClassSwitchExample),
        examplePage(examples.signal.ex_shift_option.SignalShiftOptionExample),
        examplePage(examples.signal.ex_signal_of_option.SignalOfOptionExample),
        examplePage(examples.iterable.ex_seq_join.SeqJoinExample),
        examplePage(examples.element.ex_input_values.InputValuesExample),
        examplePage(examples.eventproptransformation.ex_eventprop_stream.ThisEventsExample)
      )
    ),
    SiteModule(
      path = "fetch",
      index = docPage("", "fetch", FileAsString("/doc/fetch/index.md")),
      "" -> Seq(
        docPage("request-method", "Request method", FileAsString("/doc/fetch/request-method.md")),
        docPage("request-headers", "Request headers", FileAsString("/doc/fetch/request-headers.md")),
        docPage("request-body", "Request body", FileAsString("/doc/fetch/request-body.md")),
        docPage("request-settings", "Other request settings", FileAsString("/doc/fetch/request-settings.md")),
        docPage("response", "Handling the response", FileAsString("/doc/fetch/response.md")),
        docPage("circe", "circe support", FileAsString("/doc/fetch/circe.md")),
      ),
      "Examples" -> Seq(
        examplePage(examples.fetch.FetchPostExample),
        examplePage(examples.fetch.FetchCirceExample),
      )
    ),
    SiteModule(
      path = "websocket",
      index = docPage("", "WebSocket", FileAsString("/doc/websocket/index.md")),
      "" -> Seq(
        docPage("circe", "circe support", FileAsString("/doc/websocket/circe.md")),
      ),
      "Examples" -> Seq(
        examplePage(examples.websocket.WebSocketExample),
        examplePage(examples.websocket.WebSocketCirceExample),
      )
    ),
    SiteModule(
      path = "ui",
      index = docPage("", "UI", FileAsString("/doc/ui/index.md")),
      "Examples" -> Seq(
        examplePage(examples.ui.ex_animation.AnimationExample),
        examplePage(examples.ui.ex_transition.TransitionExample),
      )
    ),
    SiteModule(
      path = "validation",
      index = docPage("", "Validation", FileAsString("/doc/validation/index.md")),
      "Examples" -> Seq(
        examplePage(examples.validation.ex_validation.ValidationExample),
      )
    ),
//    SiteModule(
//      path = "fsm",
//      index = docPage("", "FSM", FileAsString("/doc/todo.md"))
//    ),
    SiteModule(
      path = "tailwind",
      index = docPage("", "Tailwind", FileAsString("/doc/todo.md"))
    ),
//    SiteModule(
//      path = "videojs",
//      index = docPage("", "video.js", FileAsString("/doc/todo.md"))
//    ),
//    SiteModule(
//      path = "markdown",
//      index = docPage("", "Markdown", FileAsString("/doc/todo.md"))
//    ),
//    SiteModule(
//      path = "highlight",
//      index = docPage("", "highlight.js", FileAsString("/doc/todo.md"))
//    ),
    SiteModule(
      path = "util",
      index = docPage("", "Util", FileAsString("/doc/util/index.md")),
      "Examples" -> Seq(
        examplePage(examples.util.ex_human_readable_size.HumanReadableSizeExample),
      )
    ),
  )

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}

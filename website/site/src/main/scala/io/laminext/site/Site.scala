package io.laminext.site

import io.laminext.site.examples.CodeExample
import io.laminext.site.pages.CodeExamplePage
import io.laminext.site.pages.DocumentationPage
import com.yurique.embedded.FileAsString

object Site {

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
      path = "laminext",
      index = docPage("", "laminext", FileAsString("/doc/laminext/index.md"))
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      index = docPage("", "Core", FileAsString("/doc/core/index.md")),
      navigation = Seq(
        "Signal" -> Seq(
          docPage("signal", "Signal", FileAsString("/doc/core/signal-ops.md")),
          docPage("signal-of-boolean", "Signal of Boolean", FileAsString("/doc/core/signal-of-boolean-ops.md")),
          docPage("signal-of-option", "Signal of Option", FileAsString("/doc/core/signal-of-option-ops.md")),
          docPage("signal-of-either", "Signal of Either", FileAsString("/doc/core/signal-of-either-ops.md")),
          docPage("signal-of-option-of-signal", "Signal of Option of Signal", FileAsString("/doc/core/signal-of-option-of-signal-ops.md")),
          docPage("option-of-signal", "Option of Signal", FileAsString("/doc/core/option-of-signal-ops.md")),
          docPage("var-of-boolean", "Var of Boolean", FileAsString("/doc/core/var-of-boolean-ops.md"))
        ),
        "EventStream" -> Seq(
          docPage("stream", "EventStream", FileAsString("/doc/core/eventstream-ops.md")),
          docPage("stream-of-unit", "EventStream of Unit", FileAsString("/doc/core/eventstream-of-unit-ops.md")),
          docPage("stream-of-option", "EventStream of Option", FileAsString("/doc/core/eventstream-of-option-ops.md")),
          docPage("stream-of-either", "EventStream of Either", FileAsString("/doc/core/eventstream-of-either-ops.md")),
        ),
        "EventProp" -> Seq(
          docPage("eventprop", "EventProp as Stream", FileAsString("/doc/core/eventprop-ops.md")),
        ),
        "Misc" -> Seq(
          docPage("observable-of-boolean", "Observable of Boolean", FileAsString("/doc/core/observable-of-boolean-ops.md")),
          docPage("seq", "Seq", FileAsString("/doc/core/seq-ops.md")),
          docPage("boolean", "Boolean", FileAsString("/doc/core/boolean-ops.md")),
          docPage("option", "Option", FileAsString("/doc/core/option-ops.md")),
          docPage("element", "Element", FileAsString("/doc/core/element-ops.md"))
        ),
        "Examples" -> Seq(
          examplePage(examples.signal.ex_signal_transitions.SignalTransitionsExample),
          examplePage(examples.signal.ex_var_of_boolean_toggle.VarOfBooleanToggleExample),
          examplePage(examples.signal.ex_signal_of_boolean_class_switch.SignalOfBooleanClassSwitchExample),
          examplePage(examples.signal.ex_shift_option.SignalShiftOptionExample),
          examplePage(examples.signal.ex_signal_of_option.SignalOfOptionExample),
          examplePage(examples.iterable.ex_seq_join.SeqJoinExample),
          examplePage(examples.element.ex_input_values.InputValuesExample),
          examplePage(examples.eventproptransformation.ex_eventprop_stream.EventPropStreamExample)
        )
      )
    ),
    SiteModule(
      path = "ui",
      index = docPage("", "UI", FileAsString("/doc/ui/index.md")),
      navigation = Seq(
        "Examples" -> Seq(
          examplePage(examples.ui.ex_animation.AnimationExample),
          examplePage(examples.ui.ex_transition.TransitionExample),
        )
      )
    ),
    SiteModule(
      path = "tailwind",
      index = docPage("", "Tailwind", FileAsString("/doc/todo.md"))
    ),
    SiteModule(
      path = "util",
      index = docPage("", "Util", FileAsString("/doc/util/index.md")),
      navigation = Seq(
        "Examples" -> Seq(
          examplePage(examples.util.ex_human_readable_size.HumanReadableSizeExample),
        )
      )
    ),
    SiteModule(
      path = "fsm",
      index = docPage("", "FSM", FileAsString("/doc/todo.md"))
    ),
    SiteModule(
      path = "markdown",
      index = docPage("", "Markdown", FileAsString("/doc/todo.md"))
    ),
    SiteModule(
      path = "videojs",
      index = docPage("", "video.js", FileAsString("/doc/todo.md"))
    ),
    SiteModule(
      path = "highlight",
      index = docPage("", "highlight.js", FileAsString("/doc/todo.md"))
    ),
    SiteModule(
      path = "websockets",
      index = docPage("", "WebSockets", FileAsString("/doc/todo.md")),
      navigation = Seq(
        "Examples" -> Seq(
          examplePage(examples.websocket.WebSocketExample),
        )
      )
    )
  )

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}

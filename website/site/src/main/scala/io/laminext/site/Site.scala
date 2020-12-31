package io.laminext.site

import io.laminext.site.examples.CodeExample
import io.laminext.site.pages.CodeExamplePage
import io.laminext.site.pages.DocumentationPage
import app.tulz.website.macros.FileToLiteral

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
      index = docPage("", "laminext", FileToLiteral("/doc/laminext/index.md"))
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      index = docPage("", "Core", FileToLiteral("/doc/core/index.md")),
      navigation = Seq(
        "Signal" -> Seq(
          docPage("signal", "Signal", FileToLiteral("/doc/core/signal-ops.md")),
          docPage("signal-of-boolean", "Signal of Boolean Ops", FileToLiteral("/doc/core/signal-of-boolean-ops.md")),
          docPage("signal-of-option", "Signal of Option", FileToLiteral("/doc/core/signal-of-option-ops.md")),
          docPage("signal-of-either", "Signal of Either", FileToLiteral("/doc/core/signal-of-either-ops.md")),
          docPage("signal-of-option-of-signal", "Signal of Option of Signal", FileToLiteral("/doc/core/signal-of-option-of-signal-ops.md")),
          docPage("option-of-signal", "Option of Signal", FileToLiteral("/doc/core/option-of-signal-ops.md")),
          docPage("var-of-boolean", "Var of Boolean", FileToLiteral("/doc/core/var-of-boolean-ops.md"))
        ),
        "EventStream" -> Seq(
          docPage("stream", "EventStream", FileToLiteral("/doc/core/eventstream-ops.md")),
          docPage("stream-of-unit", "EventStream of Unit", FileToLiteral("/doc/core/eventstream-of-unit-ops.md")),
          docPage("stream-of-option", "EventStream of Option", FileToLiteral("/doc/core/eventstream-of-option-ops.md")),
          docPage("stream-of-either", "EventStream of Either", FileToLiteral("/doc/core/eventstream-of-either-ops.md")),
        ),
        "EventProp" -> Seq(
          docPage("eventprop", "EventProp as Stream", FileToLiteral("/doc/core/eventprop-ops.md")),
        ),
        "Misc" -> Seq(
          docPage("observable-of-boolean", "Observable of Boolean", FileToLiteral("/doc/core/observable-of-boolean-ops.md")),
          docPage("seq", "Seq", FileToLiteral("/doc/core/seq-ops.md")),
          docPage("boolean", "Boolean", FileToLiteral("/doc/core/boolean-ops.md")),
          docPage("option", "Option", FileToLiteral("/doc/core/option-ops.md")),
          docPage("element", "Element", FileToLiteral("/doc/core/element-ops.md"))
        ),
        "Examples" -> Seq(
          examplePage(examples.signal.ex_transitions.SignalTransitionsExample),
          examplePage(examples.signal.ex_boolean_toggle.VarOfBooleanToggleExample),
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
      index = docPage("", "UI", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "tailwind",
      index = docPage("", "Tailwind", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "util",
      index = docPage("", "Util", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "fsm",
      index = docPage("", "FSM", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "markdown",
      index = docPage("", "Markdown", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "videojs",
      index = docPage("", "video.js", FileToLiteral("/doc/todo.md"))
    ),
    SiteModule(
      path = "highlight",
      index = docPage("", "highlight.js", FileToLiteral("/doc/todo.md"))
    )
  )

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}

package app.tulz.laminext.site

import app.tulz.laminext.site.examples.CodeExample
import app.tulz.laminext.site.examples.example1.TestExample
import app.tulz.laminext.site.pages.CodeExamplePage
import app.tulz.laminext.site.pages.DocumentationPage
import app.tulz.laminext.site.pages.IndexPage
import app.tulz.website.macros.FileToLiteral
import app.tulz.laminext.site.examples

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
      index = docPage("", "laminext", FileToLiteral("/doc/laminext/index.md")),
      navigation = Seq(
        "Documentation" -> Seq(
          docPage("doc-1", "Doc 1", FileToLiteral("/doc/test-doc.md"))
        ),
        "Examples" -> Seq(
          examplePage(TestExample)
        )
      )
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      index = docPage("", "Core", FileToLiteral("/doc/core/index.md")),
      navigation = Seq(
        "Signal" -> Seq(
          docPage("signal-of-boolean-ops", "Signal of Boolean Ops", FileToLiteral("/doc/core/signal-of-boolean-ops.md")),
          docPage("signal-of-option-ops", "Signal of Option Ops", FileToLiteral("/doc/core/signal-of-option-ops.md")),
          docPage("signal-of-either-ops", "Signal of Either Ops", FileToLiteral("/doc/core/signal-of-either-ops.md")),
          docPage("signal-of-option-of-signal-ops", "Signal of Option[Signal] Ops", FileToLiteral("/doc/core/signal-of-option-of-signal-ops.md")),
          docPage("signal-ops", "Signal Ops", FileToLiteral("/doc/core/signal-ops.md")),
          docPage("var-of-boolean-ops", "Var of Boolean Ops", FileToLiteral("/doc/core/var-of-boolean-ops.md"))
        ),
        "Misc" -> Seq(
          docPage("boolean-ops", "Boolean Ops", FileToLiteral("/doc/core/boolean-ops.md")),
          docPage("option-ops", "Option Ops", FileToLiteral("/doc/core/option-ops.md")),
          docPage("element-ops", "Element Ops", FileToLiteral("/doc/core/element-ops.md"))
        ),
        "Examples" -> Seq(
          examplePage(examples.signal.ex_transitions.SignalTransitionsExample),
          examplePage(examples.signal.ex_boolean_toggle.VarOfBooleanToggleExample),
          examplePage(examples.signal.ex_shift_option.SignalShiftOptionExample),
          examplePage(examples.signal.ex_signal_of_option.SignalOfOptionExample),
          examplePage(examples.iterable.ex_iterable_join.IterableJoinExample),
          examplePage(examples.element.ex_input_values.InputValuesExample),
          examplePage(examples.eventproptransformation.ex_eventprop_stream.EventPropStreamExample)
        )
      )
    ),
    SiteModule(
      path = "ui",
      index = Page("", "UI", IndexPage.render)
    ),
    SiteModule(
      path = "tailwind",
      index = Page("", "Tailwind", IndexPage.render)
    ),
    SiteModule(
      path = "util",
      index = Page("", "Util", IndexPage.render)
    ),
    SiteModule(
      path = "fsm",
      index = Page("", "FSM", IndexPage.render)
    ),
    SiteModule(
      path = "markdown",
      index = Page("", "Markdown", IndexPage.render)
    ),
    SiteModule(
      path = "videojs",
      index = Page("", "video.js", IndexPage.render)
    ),
    SiteModule(
      path = "news",
      index = Page("", "News", IndexPage.render)
    ),
    SiteModule(
      path = "releases",
      index = Page("", "Releases", IndexPage.render)
    )
  )

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}

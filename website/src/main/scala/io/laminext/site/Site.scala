package io.laminext.site

import io.laminext.site.examples.CodeExample
import io.laminext.site.pages.CodeExamplePage
import io.laminext.site.pages.DocumentationPage
import com.yurique.embedded.FileAsString
import io.laminext.BuildInfo

object Site {

  val laminextSiteVersion: String = BuildInfo.laminextSiteVersion

  val thisVersionPrefix = s"/v/$laminextSiteVersion/"

  def thisVersionHref(href: String): String =
    s"${thisVersionPrefix}${href.dropWhile(_ == '/')}"

  private def examplePage(
    example: CodeExample
  ): Page = {
    if (_allExamples == null) {
      _allExamples = Seq(example)
    } else {
      _allExamples = _allExamples :+ example
    }
    Page(example.id, example.id + "/live", example.title, CodeExamplePage(example))
  }

  private def docPage(
    path: String,
    title: String,
    markdown: String
  ): Page = Page(path, path, title, DocumentationPage(title, markdown))

  val indexModule: SiteModule =
    SiteModule(
      path = "",
      title = "laminext",
      index = docPage("", "laminext", FileAsString("/doc/index.md"))
    )

  val modules: Seq[SiteModule] = Seq(
    indexModule,
    SiteModule(
      path = "core",
      title = "Core",
      index = docPage("", "Core", FileAsString("/doc/core/index.md")),
      "Source"         -> Seq(
        docPage("source", "Source", FileAsString("/doc/core/source.md")),
        docPage("source-of-boolean", "Source of Boolean", FileAsString("/doc/core/source-of-boolean.md")),
        docPage("source-of-option", "Source of Option", FileAsString("/doc/core/source-of-option.md")),
        docPage("source-of-either", "Source of Either", FileAsString("/doc/core/source-of-either.md")),
      ),
      "Signal"         -> Seq(
        docPage("signal", "Signal", FileAsString("/doc/core/signal.md")),
        docPage("signal-of-boolean", "Signal of Boolean", FileAsString("/doc/core/signal-of-boolean.md")),
        docPage("signal-of-option", "Signal of Option", FileAsString("/doc/core/signal-of-option.md")),
        docPage("signal-of-either", "Signal of Either", FileAsString("/doc/core/signal-of-either.md")),
        docPage("signal-of-option-of-signal", "Signal of Option of Signal", FileAsString("/doc/core/signal-of-option-of-signal.md")),
        docPage("option-of-signal", "Option of Signal", FileAsString("/doc/core/option-of-signal.md")),
        docPage("var-of-boolean", "Var of Boolean", FileAsString("/doc/core/var-of-boolean.md")),
      ),
      "EventStream"    -> Seq(
        docPage("eventstream", "EventStream", FileAsString("/doc/core/eventstream.md")),
        docPage("eventstream-of-option", "EventStream of Option", FileAsString("/doc/core/eventstream-of-option.md")),
        docPage("eventstream-of-either", "EventStream of Either", FileAsString("/doc/core/eventstream-of-either.md")),
      ),
      "EventProp"      -> Seq(
        docPage("this-event", "thisEvents", FileAsString("/doc/core/this-events.md")),
      ),
      "Input Elements" -> Seq(
        docPage("input-values", "Input Values", FileAsString("/doc/core/input-values.md"))
      ),
      "Misc"           -> Seq(
        docPage("helpers", "Small helpers", FileAsString("/doc/core/helpers.md")),
      ),
      "Examples"       -> Seq(
        examplePage(examples.signal.ex_signal_transitions.SignalTransitionsExample),
        examplePage(examples.signal.ex_var_of_boolean_toggle.VarOfBooleanToggleExample),
        examplePage(examples.signal.ex_signal_of_boolean_class_switch.SignalOfBooleanClassSwitchExample),
        examplePage(examples.signal.ex_shift_option.SignalShiftOptionExample),
        examplePage(examples.signal.ex_signal_of_option.SignalOfOptionExample),
        examplePage(examples.iterable.ex_seq_join.SeqJoinExample),
        examplePage(examples.element.ex_input_values.InputValuesExample),
        examplePage(examples.eventproptransformation.ex_eventprop_stream.ThisEventsExample),
        examplePage(examples.observable.ex_observable_switching_bind.AddSwitchingObserverExample),
        examplePage(examples.sequentially.SequentiallyExample),
        examplePage(examples.resizeobserver.ResizeObserverExample),
      )
    ),
    SiteModule(
      path = "fetch",
      title = "fetch",
      index = docPage("", "fetch", FileAsString("/doc/fetch/index.md")),
      ""               -> Seq(
        docPage("request-method", "Request method", FileAsString("/doc/fetch/request-method.md")),
        docPage("request-headers", "Request headers", FileAsString("/doc/fetch/request-headers.md")),
        docPage("request-body", "Request body", FileAsString("/doc/fetch/request-body.md")),
        docPage("request-settings", "Other request settings", FileAsString("/doc/fetch/request-settings.md")),
        docPage("response", "Handling the response", FileAsString("/doc/fetch/response.md")),
        docPage("circe", "circe support", FileAsString("/doc/fetch/circe.md")),
        docPage("upickle", "upickle support", FileAsString("/doc/fetch/upickle.md")),
      ),
      "Examples"       -> Seq(
        examplePage(examples.fetch.FetchPostExample),
        examplePage(examples.fetch.FetchCirceExample),
        examplePage(examples.fetch.FetchUpickleExample),
      )
    ),
    SiteModule(
      path = "websocket",
      title = "websocket",
      index = docPage("", "WebSocket", FileAsString("/doc/websocket/index.md")),
      ""               -> Seq(
        docPage("circe", "circe support", FileAsString("/doc/websocket/circe.md")),
        docPage("upickle", "upickle support", FileAsString("/doc/websocket/upickle.md"))
      ),
      "Examples"       -> Seq(
        examplePage(examples.websocket.WebSocketExample),
        examplePage(examples.websocket.WebSocketCirceExample),
        examplePage(examples.websocket.WebSocketUpickleExample)
      )
    ),
    SiteModule(
      path = "ui",
      title = "UI",
      index = docPage("", "UI", FileAsString("/doc/ui/index.md")),
      ""               -> Seq(
        docPage("card", "Card", FileAsString("/doc/ui/card.md")),
        docPage("file-input", "File Input", FileAsString("/doc/ui/file-input.md")),
        docPage("progress-bar", "Progress Bar", FileAsString("/doc/ui/progress-bar.md")),
        docPage("transition", "Transition", FileAsString("/doc/ui/transition.md")),
        docPage("modal", "Modal", FileAsString("/doc/ui/modal.md")),
        docPage("animation", "Animation", FileAsString("/doc/ui/animation.md")),
      ),
      "Examples"       -> Seq(
        examplePage(examples.ui.ex_card.CardExample),
        examplePage(examples.ui.ex_fileinput.FileInputExample),
        examplePage(examples.ui.ex_progress_bar.ProgressBarExample),
        examplePage(examples.ui.ex_modal.ModalExample),
        examplePage(examples.ui.ex_animation.AnimationExample),
        examplePage(examples.ui.ex_transition.TransitionExample),
        examplePage(examples.ui.ex_drag_drop.DragDropExample),
        examplePage(examples.ui.ex_drag_drop_2.DragDropExample2),
      )
    ),
    SiteModule(
      path = "validation",
      title = "Validation",
      index = docPage("", "Validation", FileAsString("/doc/validation/index.md")),
      ""               -> Seq(
        docPage("cats", "cats", FileAsString("/doc/validation/cats.md")),
      ),
      "Examples"       -> Seq(
        examplePage(examples.validation.ex_validation.ValidationExample),
        examplePage(examples.validation.ex_validation_cats.ValidationCatsExample),
      )
    ),
//    SiteModule(
//      path = "fsm",
//      index = docPage("", "FSM", FileAsString("/doc/todo.md"))
//    ),
    SiteModule(
      path = "tailwind",
      title = "tailwind",
      index = docPage("", "Tailwind", FileAsString("/doc/tailwind/index.md")),
      ""               -> Seq(
        docPage("transition", "Transition", FileAsString("/doc/tailwind/transition.md")),
        //        docPage("theme", "Theme", FileAsString("/doc/tailwind/theme.md")),
//        docPage("buttons", "Buttons", FileAsString("/doc/tailwind/buttons.md")),
      )
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
      title = "Utilities",
      index = docPage("", "Util", FileAsString("/doc/util/index.md")),
      "Examples"       -> Seq(
        examplePage(examples.util.ex_human_readable_size.HumanReadableSizeExample),
      )
    ),
    SiteModule(
      path = "news",
      title = "News",
      index = docPage("", "News", FileAsString("/doc/news/index.md"))
    ),
  )

  private var _allExamples: Seq[CodeExample] = _
  def allExamples: Seq[CodeExample]          = _allExamples

  def findModule(path: String): Option[SiteModule] =
    modules.find(_.path == path)

}

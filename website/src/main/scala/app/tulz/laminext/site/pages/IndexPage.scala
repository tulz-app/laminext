package app.tulz.laminext.site.pages

import app.tulz.laminar.ext._
import app.tulz.tailwind._
import com.raquo.laminar.api.L.{animation => _, transition => _, _}

object IndexPage {

  val render: PageRender = page("Index Page") {
    val show = Var(true)

    val bounceSignal = Var(Option.empty[String])

    div(
      main(
        cls := "flex flex-col space-y-2",
        div(
          // testing
          cls := "p-4 flex h-48 space-x-4 animated",
          div(
            cls := "p-4 space-x-4",
            button.btn.xl.fill.blue("toggle", onClick --> { _ => show.toggle() })
          ),
          div(
            cls := "p-8 w-64  bg-blue-600 text-blue-50",
            transition(show.signal),
            "I should transition!"
          )
        ),
        div( // testing
          cls := "p-4 flex h-48 space-x-4 animated",
          div(
            cls := "p-4 space-x-4",
            button.btn.xl.fill.blue(
              "toggle",
              onClick --> { _ =>
                bounceSignal.update(s =>
                  if (s.isEmpty) {
                    Some("animate-wiggle")
                  } else {
                    None
                  }
                )
              }
            )
          ),
          div(
            cls := "p-8 w-64  bg-blue-600 text-blue-50",
            animate(bounceSignal.signal),
            "I should bounce!"
          )
        ),
        div( // testing
             cls := "p-4",
             input(`type` := "file", multiple := true, idAttr := "file")
        ),
        div( // testing
             cls := "p-4",
             div.card.wrap(
               div.card.header(
                 div.card.title("header")
               ),
               div.card.body("body"),
               div.card.footer("footer")
             )
        ),
        div( // testing
          cls := "p-4 space-y-4",
          div(
            cls := "p-4 space-x-4",
            button.btn.tiny.fill.blue("tiny.fill.blue"),
            button.btn.xs.fill.blue("xs.fill.blue"),
            button.btn.sm.fill.blue("sm.fill.blue"),
            button.btn.md.fill.blue("md.fill.blue"),
            button.btn.lg.fill.blue("lg.fill.blue"),
            button.btn.xl.fill.blue("xl.fill.blue")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.tiny.outline.blue("tiny.outline.blue"),
            button.btn.xs.outline.blue("xs.outline.blue"),
            button.btn.sm.outline.blue("sm.outline.blue"),
            button.btn.md.outline.blue("md.outline.blue"),
            button.btn.lg.outline.blue("lg.outline.blue"),
            button.btn.xl.outline.blue("xl.outline.blue")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.blue("fill.blue"),
            button.btn.lg.fill.blue(disabled := true, "fill.blue.disabled"),
            button.btn.lg.outline.blue("outline.blue"),
            button.btn.lg.text.blue("text.blue")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.green("fill.green"),
            button.btn.lg.fill.green(disabled := true, "fill.green.disabled"),
            button.btn.lg.outline.green("outline.green"),
            button.btn.lg.text.green("text.green")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.indigo("fill.indigo"),
            button.btn.lg.fill.indigo(disabled := true, "fill.indigo.disabled"),
            button.btn.lg.outline.indigo("outline.indigo"),
            button.btn.lg.text.indigo("text.indigo")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.red("fill.red"),
            button.btn.lg.fill.red(disabled := true, "fill.red.disabled"),
            button.btn.lg.outline.red("outline.red"),
            button.btn.lg.text.red("text.red")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.purple("fill.purple"),
            button.btn.lg.fill.purple(disabled := true, "fill.purple.disabled"),
            button.btn.lg.outline.purple("outline.purple"),
            button.btn.lg.text.purple("text.purple")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.yellow("fill.yellow"),
            button.btn.lg.fill.yellow(disabled := true, "fill.yellow.disabled"),
            button.btn.lg.outline.yellow("outline.yellow"),
            button.btn.lg.text.yellow("text.yellow")
          ),
          div(
            cls := "p-4 space-x-4 bg-cool-gray-900",
            button.btn.lg.fill.white("fill.white"),
            button.btn.lg.fill.white(disabled := true, "fill.white.disabled"),
            button.btn.lg.outline.white("outline.white"),
            button.btn.lg.text.white("text.white")
          ),
          div(
            cls := "p-4 space-x-4",
            button.btn.lg.fill.black("fill.black"),
            button.btn.lg.fill.black(disabled := true, "fill.black.disabled"),
            button.btn.lg.outline.black("outline.black"),
            button.btn.lg.text.black("text.black")
          ),
          // group
          div(
            cls := "p-4",
            div(
              cls := "relative z-0 inline-flex shadow-sm rounded-md",
              button.btn.group.first.lg.fill.blue("group.first.fill.blue"),
              button.btn.group.inner.lg.fill.blue("group.inner.fill.blue"),
              button.btn.group.last.lg.fill.blue("group.last.fill.blue")
            )
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.green("group.first.fill.green"),
            button.btn.group.inner.lg.fill.green("group.inner.fill.green"),
            button.btn.group.last.lg.fill.green("group.last.fill.green")
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.indigo("group.first.fill.indigo"),
            button.btn.group.inner.lg.fill.indigo("group.inner.fill.indigo"),
            button.btn.group.last.lg.fill.indigo("group.last.fill.indigo")
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.red("group.first.fill.red"),
            button.btn.group.inner.lg.fill.red("group.inner.fill.red"),
            button.btn.group.last.lg.fill.red("group.last.fill.red")
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.purple("group.first.fill.purple"),
            button.btn.group.inner.lg.fill.purple("group.inner.fill.purple"),
            button.btn.group.last.lg.fill.purple("group.last.fill.purple")
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.yellow("group.first.fill.yellow"),
            button.btn.group.inner.lg.fill.yellow("group.inner.fill.yellow"),
            button.btn.group.last.lg.fill.yellow("group.last.fill.yellow")
          ),
          div(
            cls := "p-4 space-x-4 bg-cool-gray-900",
            button.btn.group.first.lg.fill.white("group.first.fill.white"),
            button.btn.group.inner.lg.fill.white("group.inner.fill.white"),
            button.btn.group.last.lg.fill.white("group.last.fill.white")
          ),
          div(
            cls := "p-4",
            button.btn.group.first.lg.fill.black("group.first.fill.black"),
            button.btn.group.inner.lg.fill.black("group.inner.fill.black"),
            button.btn.group.last.lg.fill.black("group.last.fill.black")
          )
        )
      )
    )
  }

}

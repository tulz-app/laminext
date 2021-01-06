package io.laminext.syntax

import io.laminext.HtmlEntities
import io.laminext.domext.ExtraEvents
import io.laminext.util.SmartClass

trait AllSyntax
    extends SignalSyntax
    with SignalOfEitherSyntax
    with SignalOfOptionSyntax
    with SignalOfTuple2Syntax
    with SignalOfOptionOfSignalSyntax
    with SignalOfBooleanSyntax
    with EventStreamSyntax
    with EventStreamOfUnitSyntax
    with EventStreamOfEitherSyntax
    with EventStreamOfOptionSyntax
    with ObservableOfBooleanSyntax
    with VarOfBooleanSyntax
    with EventPropTransformationSyntax
    with ReactiveEventPropSyntax
    with FutureOfEitherSyntax
    with HtmlTagSyntax
    with SvgTagSyntax
    with ReactiveHtmlElementSyntax
    with ReactiveSvgElementSyntax
    with InputElementSyntax
    with TextAreaSyntax
    with OptionOfSignalSyntax
    with SeqSyntax
    with MiscSyntax
    with HtmlEntities
    with SmartClass
    with ExtraEvents
    with ReceiversSyntax {

  type AmAny = io.laminext.AmAny

}

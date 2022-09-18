package io.laminext.syntax

import io.laminext.ui.syn.AnimationCreate
import io.laminext.ui.syn.BaseSyntax
import io.laminext.ui.syn.CardSyntax
import io.laminext.ui.syn.FileInputCreate
import io.laminext.ui.syn.ModalCreate
import io.laminext.ui.syn.ProgressBarCreate
import io.laminext.ui.syn.TransitionCreate

object ui extends BaseSyntax with FileInputCreate with ProgressBarCreate with ModalCreate with TransitionCreate with AnimationCreate with CardSyntax

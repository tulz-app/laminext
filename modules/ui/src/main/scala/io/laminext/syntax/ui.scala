package io.laminext.syntax

import io.laminext.ui.syn.AnimationCreate
import io.laminext.ui.syn.BaseSyntax
import io.laminext.ui.syn.FileInputCreate
import io.laminext.ui.syn.TransitionCreate

object ui extends BaseSyntax with FileInputCreate with TransitionCreate with AnimationCreate

ThisBuild / githubWorkflowPublishTargetBranches := Seq()
ThisBuild / githubWorkflowBuild := Seq(WorkflowStep.Sbt(List("test", "website/fastLinkJS")))

sealed abstract class JSEnv(val isBrowser: Boolean)
object JSEnv {
  case object Chrome      extends JSEnv(true)
  case object Firefox     extends JSEnv(true)
  case object JSDOMNodeJS extends JSEnv(false)
  case object NodeJS      extends JSEnv(false)
}

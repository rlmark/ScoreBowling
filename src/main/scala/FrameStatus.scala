trait FrameStatus
case class Pending(currentFrame: Frame) extends FrameStatus
case class Score(curentFrame: Frame, scoreValue: Int) extends FrameStatus

object FrameStatus{
  def apply(frame: Frame): FrameStatus = {
    frame match {
      case f@OpenFrame(b1, b2, _, true, _) => Score(f, b1 + b2)
      case f@Spare(b1, b2, b3, true, _) => Score(f, b1 + b2 + b3)
      case f@Strike(b1, b2, b3, true, _) => Score(f, b1 + b2 + b3)
      case f@_ => Pending(f)
    }
  }
}
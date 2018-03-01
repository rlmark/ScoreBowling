trait FrameStatus
case class Pending(currentFrame: Frame) extends FrameStatus
case class Score(curentFrame: Frame, scoreValue: Int) extends FrameStatus

object FrameStatus{
  def apply(frame: Frame): FrameStatus = {
    if (frame.complete) Score(frame, frame.balls.sum) else Pending(frame)
  }
}
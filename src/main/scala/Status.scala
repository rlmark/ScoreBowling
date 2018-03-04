trait Status
case class Pending(currentFrame: Frame) extends Status
case class Score(curentFrame: Frame, scoreValue: Int) extends Status

object Status{
  def apply(frame: Frame): Status = {
    if (frame.complete) Score(frame, frame.balls.sum) else Pending(frame)
  }
}
sealed trait Frame {
  val ball1: Int
  val ball2: Int
  val ball3: Int
  val isComplete: Boolean
  val score: Option[Int]  // TODO use frameStatus

  def calculateSingleFrameScore(frame: Frame, currentFrame: Int ): FrameStatus = {
    frame match {
      case OpenFrame(b1, b2, _, true, _) => Score(b1 + b2)
      case Spare(b1, b2, b3, true, _) => Score(b1 + b2 + b3)
      case Strike(b1, b2, b3, true, _) => Score(b1 + b2 + b3)
      case f@_ => Pending(f)
    }
  }
}

case class OpenFrame(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame
case class Spare(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame
case class Strike(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame


trait FrameStatus
case class Pending(currentFrame: Frame) extends FrameStatus
case class Score(scoreValue: Int) extends FrameStatus

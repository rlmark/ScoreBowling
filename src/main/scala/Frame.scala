sealed trait Frame {
  val ball1: Int
  val ball2: Int
  val ball3: Int
//  val balls: List[Int]
  val isComplete: Boolean
  val score: Option[Int]  // TODO use frameStatus

  def calculateSingleFrameScore(frame: Frame, currentFrame: Int ): FrameStatus = {
    frame match {
      case f@OpenFrame(b1, b2, _, true, _) => Score(f, b1 + b2)
      case f@Spare(b1, b2, b3, true, _) => Score(f, b1 + b2 + b3)
      case f@Strike(b1, b2, b3, true, _) => Score(f, b1 + b2 + b3)
      case f@_ => Pending(f)
    }
  }
}

case class OpenFrame(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame
case class Spare(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame
case class Strike(ball1: Int, ball2: Int, ball3:Int, isComplete: Boolean, score: Option[Int]) extends Frame

object Frame {
  def apply(turn: List[Int]): Frame = {
    turn match {
      case firstBall :: secondBall :: Nil if firstBall + secondBall < 10 =>
        OpenFrame(firstBall, secondBall, -1, isComplete = true, None)
      case firstBall :: secondBall :: Nil if firstBall + secondBall == 10 =>
        Spare(firstBall, secondBall, -1, isComplete = false, None)
      case firstBall :: Nil if firstBall == 10 =>
        Strike( 10, -1, -1, isComplete = false, None)
      case firstBall :: secondBall:: thirdBall :: Nil if firstBall == 10 =>
        Strike(firstBall, secondBall, thirdBall, isComplete = true, None)
      case firstBall :: secondBall:: thirdBall :: Nil if firstBall + secondBall == 10 =>
        Spare(firstBall, secondBall, thirdBall, isComplete = true, None)
    }
  }

//  def updateFrame(turn: List[Int], previousFrame: Frame): Frame = {
//    turn match {
//      case firstBall :: Nil if firstBall == 10 && previousFrame.ball2 == 10
//    }
//  }
}

trait FrameStatus
case class Pending(currentFrame: Frame) extends FrameStatus
case class Score(curentFrame: Frame, scoreValue: Int) extends FrameStatus

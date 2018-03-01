sealed trait Frame {
  val balls: List[Int]
  val complete: Boolean
  val score: Option[Int]  // TODO use frameStatus
}

case class OpenFrame(balls: List[Int], complete: Boolean, score: Option[Int]) extends Frame
case class Spare(balls: List[Int], complete: Boolean, score: Option[Int]) extends Frame
case class Strike(balls: List[Int], complete: Boolean, score: Option[Int]) extends Frame

object Frame {
  def apply(turn: List[Int]): Frame = {
    turn match {
      case t@firstBall :: secondBall :: Nil if firstBall + secondBall < 10 =>
        OpenFrame(t, complete = true, None)
      case t@firstBall :: secondBall :: Nil if firstBall + secondBall == 10 =>
        Spare(t, complete = false, None)
      case t@firstBall :: Nil if firstBall == 10 =>
        Strike( t, complete = false, None)
      case t@firstBall :: secondBall:: thirdBall :: Nil if firstBall == 10 =>
        Strike(t, complete = true, None)
      case t@firstBall :: secondBall:: thirdBall :: Nil if firstBall + secondBall == 10 =>
        Spare(t, complete = true, None)
    }
  }

//  def updateFrame(turn: List[Int], previousFrame: Frame): Frame = {
//    turn match {
//      // weird case if you get two strikes in a row
//      case firstBall :: Nil if firstBall == 10 && previousFrame.ball1 == 10 =>
//        previousFrame.
//    }
//  }
}

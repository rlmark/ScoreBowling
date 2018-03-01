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

  def updateFrame(turn: List[Int], previousFrame: Frame): Frame = {
    previousFrame match {
      case Spare(bs, _, _) =>
        Spare(bs :+ turn.head, complete = true, None) // if the previous frame was a spare, get the first roll from turn
      case Strike(bs, _, _) if turn.lengthCompare(1) =>
        Strike(bs ++ turn, complete = false, None) // Two strikes in a row
      case Strike(bs, _, _) =>
        val totalBalls = bs ++ turn
        Strike(totalBalls.slice(0, 4), complete = true, None) // takes care of (10, 10, 4) and (10, 4, 2) case
    }
  }
}

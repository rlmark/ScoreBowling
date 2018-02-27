import scala.util.Random

class Player(val name: String) {
  def takeTurn(r: Random = Random): Turn = {
    val first = r.nextInt(11) // Upper bound is exclusive
    if (first == 10) StandardTurn(first, None) else StandardTurn(first, Some(r.nextInt( 11 - first)))
  }
}

sealed trait Turn
case class StandardTurn(firstBall: Int, secondBall: Option[Int]) extends Turn
case class LastTurn(firstBall: Int, secondBall: Option[Int], thirdBall: Option[Int]) extends Turn

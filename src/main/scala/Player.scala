import scala.util.Random

class Player(val name: String) {
  def takeTurn(frameIndex: Int, r: Random = Random): List[Int] = {
    val first = r.nextInt(11) // Upper bound is exclusive
    val second = r.nextInt( 11 - first )
println("value at first is " + first)
    if (frameIndex == 10) {
      if (first == 10) {
        val localSecond = r.nextInt(11)
        val localThird = if (localSecond == 10) r.nextInt( 11 ) else r.nextInt(11 - localSecond)
        List(first, localSecond, localThird)
      }
      else if (first + second == 10) {
        List(first, second, r.nextInt(11))
      } else {
        List(first, second)
      }
    } else
     if (first == 10) List(first) else List(first, second)

  }
}

//sealed trait Turn
//case class StandardTurn(firstBall: Int, secondBall: Option[Int]) extends Turn
//case class LastTurn(firstBall: Int, secondBall: Option[Int], thirdBall: Option[Int]) extends Turn

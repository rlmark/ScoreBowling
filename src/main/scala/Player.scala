import scala.util.Random

class Player(val name: String) {
  val totalScore: Int = 0

  def takeTurn(r: Random = Random): Rolls = {
    val first = r.nextInt(11) // Upper bound is exclusive
    if (first == 10) Rolls(first, None) else Rolls(first, Some(r.nextInt( 11 - first)))
  }

}

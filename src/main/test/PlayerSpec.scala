import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class PlayerSpec extends FlatSpec with Matchers with MockitoSugar {
  "takeTurn" should "generate valid turns for strike" in {
    val p = new Player("t")
    val mockRandom = mock[Random]
    when(mockRandom.nextInt(11)).thenReturn(10)
    p.takeTurn(mockRandom) shouldBe Rolls(10, None)
  }
  it should "generate valid turns for spare" in {
    val p = new Player("t")
    val r = Random
    for (_ <- 1 to 199){
      val result = p.takeTurn(r)
      if (result.first + result.second.getOrElse(0) > 10) {
        fail("Offending roll: " + result.toString)
      }
    }
  }
}

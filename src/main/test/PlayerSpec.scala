import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Random

class PlayerSpec extends FlatSpec with Matchers with MockitoSugar {
  "takeTurn" should "generate valid turns for strike" in {
    val p = new Player("t")
    val mockRandom = mock[Random]
    when(mockRandom.nextInt(11)).thenReturn(10)
    p.takeTurn(1, mockRandom) shouldBe List(10)
  }
  it should "generate valid turns for spare" in {
    val p = new Player("t")
    for (_ <- 1 to 199){
      val result = p.takeTurn(2)
      println(result)
      if (result.sum > 30) {
        fail("Offending roll: " + result.toString)
      }
    }
  }
  it should "generate valid last turns for strike last turn" in {
    val p = new Player("t")
    val mockRandom = mock[Random]
    when(mockRandom.nextInt(11)).thenReturn(10)
    when(mockRandom.nextInt(11)).thenReturn(10)
    when(mockRandom.nextInt(11)).thenReturn(10)
    p.takeTurn(10, mockRandom) shouldBe List(10, 10, 10)
  }

  it should "generate valid turns for last turns" in {
    val p = new Player("t")
    for (_ <- 1 to 199){
      val result = p.takeTurn(10)
      println(result)
      if (result.sum > 30) {
        fail("Offending roll: " + result.toString)
      }
    }
  }

}

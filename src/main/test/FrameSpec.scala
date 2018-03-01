import org.scalatest.{FlatSpec, Matchers}

class FrameSpec extends FlatSpec with Matchers{

  "Frame" should "create the openFrame when rolls total is less than 10" in {
    val turn = List(4, 3)
    val frame = Frame(turn)
    frame shouldBe a[OpenFrame]
  }
  it should "create the Strike frame when first roll is 10" in {
    val turn = List(10)
    val frame = Frame(turn)
    frame shouldBe a[Strike]
  }
  it should "create the Spare frame when first and second roll total 10" in {
    val turn = List(3, 7)
    val frame = Frame(turn)
    frame shouldBe a[Spare]
  }
  it should "create a valid last Spare frame" in {
    // Need a method to create a frame from a turn
    val lastTurn = List(9, 1, 10)
    val frame = Frame(lastTurn)
    frame shouldBe a[Spare]
  }
  it should "create a valid last Strike frame" in {
    // Need a method to create a frame from a turn
    val lastTurn = List(10, 10, 10)
    val frame = Frame(lastTurn)
    frame shouldBe a[Strike]
  }
}

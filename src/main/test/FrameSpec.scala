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

  "update" should "not update an open frame given new turn" in {
    val turn = List(1,4)
    val previousFrame = OpenFrame(List(6,1), true)
    val result = Frame.update(turn, previousFrame)
    result shouldBe previousFrame
  }

  it should " update a spare frame given new turn" in {
    val turn = List(1,7)
    val previousFrame = Spare(List(6,4), false)
    val result = Frame.update(turn, previousFrame)
    result shouldBe Spare(List(6,4,1), true)
  }

  it should "update a strike frame given new open frame" in {
    val turn = List(1,4)
    val previousFrame = Strike(List(10), false)
    val result = Frame.update(turn, previousFrame)
    result shouldBe Strike(List(10,1,4), true)
  }

  it should "update a strike frame given new Strike" in {
    val turn = List(10)
    val previousFrame = Strike(List(10), false)
    val result = Frame.update(turn, previousFrame)
    result shouldBe Strike(List(10,10), false)
  }

  it should "update a double strike frame given new frame" in {
    val turn = List(1,4)
    val previousFrame = Strike(List(10, 10), false)
    val result = Frame.update(turn, previousFrame)
    result shouldBe Strike(List(10,10,1), true)
  }
}

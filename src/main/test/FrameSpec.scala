import org.scalatest.{FlatSpec, Matchers}

class FrameSpec extends FlatSpec with Matchers{

  "Frame" should "create the openFrame when rolls total is less than 10" in {
    val rolls = Rolls(4, Some(3))
    val frame = Frame(rolls)
    frame shouldBe a[OpenFrame]
  }
  it should "create the Strike frame when first roll is 10" in {
    val rolls = Rolls(10, None)
    val frame = Frame(rolls)
    frame shouldBe a[Strike]
  }
  it should "create the Spare frame when first and second roll total 10" in {
    val rolls = Rolls(3, Some(7))
    val frame = Frame(rolls)
    frame shouldBe a[Spare]
  }
}

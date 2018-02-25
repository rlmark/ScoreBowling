import org.scalatest.{FlatSpec, Matchers}

class GameSpec extends FlatSpec with Matchers {
  "calculateSingleFrameScore" should "return frame score for open frame" in {
    val game = new Game()
    val frames = Vector(OpenFrame(Rolls(2,Some(3))))
    game.calculateSingleFrameScore(frames, 0) shouldBe 5
  }
  it should "return frame score for Spare when subsequent frame is open frame" in {
    val game = new Game()
    val frames = Vector(Spare(Rolls(8,Some(2))), OpenFrame(Rolls(2,Some(3))))
    game.calculateSingleFrameScore(frames, 0) shouldBe 12
  }
  it should "return frame score for Spare when subsequent frame is strike" in {
    val game = new Game()
    val frames = Vector(Spare(Rolls(8,Some(2))), Strike(Rolls(10, None)))
    game.calculateSingleFrameScore(frames, 0) shouldBe 20
  }
  it should "return frame score for Strike when subsequent frame is open frame" in {
    val game = new Game()
    val frames = Vector(Strike(Rolls(10,None)), OpenFrame(Rolls(2,Some(3))))
    game.calculateSingleFrameScore(frames, 0) shouldBe 15
  }
  it should "return frame score for Strike when subsequent frame is Strike" in {
    val game = new Game()
    val frames = Vector(Strike(Rolls(10,None)), Strike(Rolls(10,None)), OpenFrame(Rolls(4,Some(5))))
    game.calculateSingleFrameScore(frames, 0) shouldBe 24
  }
}

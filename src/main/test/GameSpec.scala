import org.scalatest.{FlatSpec, Matchers}

class GameSpec extends FlatSpec with Matchers {

  "play" should "play a game" in {
    val players = Vector(new Player("P1"), new Player("P2"))
    val game = new Game(players)
    println(game.play(players))

  }
  "updateBoard" should "not update previous Open frames with new turn" in {
    val game = new Game()
    val turn = List (1,3)
    val previousFrames = Vector(
      Status(Frame(List(4,5))),
      Status(Frame(List(2,2)))
    )

    val result = game.updateBoard(turn, previousFrames)

    val expectedStatuses = previousFrames
    result should contain theSameElementsInOrderAs expectedStatuses
  }

  it should " update previous Spare frame with new turn" in {
    val game = new Game()
    val turn = List (1,3)
    val previousFrames = Vector(
      Status(Frame(List(4,5))),
      Status(Frame(List(9,1)))
    )

    val result = game.updateBoard(turn, previousFrames)
    val expectedStatuses = Vector(
      Score(OpenFrame(List(4,5), complete = true), 9),
      Score(Spare(List(9,1,1), complete = true),11)
    )

    result should contain theSameElementsInOrderAs expectedStatuses
  }

  it should "update previous Strike frame with new turn" in {
    val game = new Game()
    val turn = List (1,3)
    val previousFrames = Vector(
      Status(Frame(List(4, 3))),
      Status(Frame(List(10)))
    )

    val result = game.updateBoard(turn, previousFrames)
    val expectedStatuses = Vector(
      Score(OpenFrame(List(4,3), complete = true), 7),
      Score(Strike(List(10, 1, 3), complete = true),14)
    )

    result should contain theSameElementsInOrderAs expectedStatuses
  }

  it should "update previous Strike frame with new Strike and keep status Pending" in {
    val game = new Game()
    val turn = List (10)
    val previousFrames = Vector(
      Status(Frame(List(10)))
    )

    val result = game.updateBoard(turn, previousFrames)
    val expectedStatuses = Vector(
      Pending(Strike(List(10, 10), complete = false))
    )

    result should contain theSameElementsInOrderAs expectedStatuses
  }

  it should "update 2 previous Strike frames" in {
    val game = new Game()
    val turn = List(2,3)
    val previousFrames = Vector(
      Status(Strike(List(10, 10), false)),
      Status(Strike(List(10), false))
    )

    val result = game.updateBoard(turn, previousFrames)
    val expectedStatuses = Vector(
      Score(Strike(List(10, 10, 2), true), 22),
      Score(Strike(List(10, 2, 3), true), 15)
    )

    result should contain theSameElementsInOrderAs expectedStatuses

  }
}

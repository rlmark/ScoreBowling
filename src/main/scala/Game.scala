import scala.collection.mutable

class Game(players: Vector[Player] = Vector(new Player("1"), new Player("2"))) { // TODO: get from elsewhere

  val board: scala.collection.mutable.Map[Player, Vector[Status]] = mutable.Map()
  players foreach {p =>
    board += p -> Vector.empty
  }
  println("initializing empty board " + board)

  def mutablePlay(): mutable.Map[Player, Vector[Status]] = {
    for (i <- 1 to 10) {
      players.foreach { player =>
        val playerSpecificFrames = board(player)
//        println("Value of board at start of turn " + playerSpecificFrames.mkString(" | "))
        val turnTaken = player.takeTurn(i)
//        println("Current turn values " + turnTaken.mkString(", "))
        val currentFrameStatus = Status(Frame(turnTaken))
//        println("Current Frame Status " + currentFrameStatus)
        val updatedPreviousFrames = updateBoard(turnTaken, playerSpecificFrames)
//        println("previous frames updated with new turn " + updatedPreviousFrames.mkString(" | "))

        val rewriteBoard: Vector[Status] = updatedPreviousFrames :+ currentFrameStatus
        board += (player -> rewriteBoard)

        println(player.name + "||" + board(player).mkString(" | "))
      }
    }
    board
  }

  // update frame method, raw list comes in, it calculatesTheScore and appends it to the FrameStatusList.
  def updateBoard(turn: List[Int], playerFrameStatuses: Vector[Status]): Vector[Status] = {
    playerFrameStatuses.map {
      case Pending(frameToUpdate) => Status(Frame.update(turn, frameToUpdate))
      case finished@Score(_, _) => finished
    }
  }





}


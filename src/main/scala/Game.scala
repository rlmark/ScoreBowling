

class Game(players: Vector[Player] = Vector(new Player("1"), new Player("2"))) { // TODO: get elsewhere

  val board: Map[Player, Vector[Status]] = players.map(_ -> Vector()).toMap

  def play(players: Vector[Player]): Map[Player, Vector[Status]] = {
    for (i <- 1 to 10) {
      val test: Seq[Map[Player, Vector[Status]]] = players.map { player =>
        val playerSpecificFrames = board(player)
        val turnTaken = player.takeTurn(i)
        val currentFrameStatus = Status(Frame(turnTaken))
        val updatedPreviousFrames = updateBoard(turnTaken, playerSpecificFrames)
        val rewriteBoard: Vector[Status] = currentFrameStatus +: updatedPreviousFrames
        val x = board + (player -> rewriteBoard)
        println("board being updated for player " + x)
        x
      }
    }
    board

//    val t: Map[Player, Vector[FrameStatus]] = board.map {case (player, playersFrames) =>
//      val turnTaken = player.takeTurn(8)
//      val currentFrame = Frame(turnTaken)
//      val currentFrameStatus = FrameStatus(currentFrame)
//      val updatedPreviousFrames = updateBoard(turnTaken, playersFrames)
//      val rewriteBoard: Vector[FrameStatus] = currentFrameStatus +: updatedPreviousFrames
//      player -> rewriteBoard
//    } // FUTURE WAY TO DO IT
  }

  // update frame method, raw list comes in, it calculatesTheScore and appends it to the FrameStatusList.
  def updateBoard(turn: List[Int], playerFrameStatuses: Vector[Status]): Vector[Status] = {
    playerFrameStatuses.map {
      case Pending(frameToUpdate) => Status(Frame.update(turn, frameToUpdate))
      case finished@Score(_, _) => finished
    }
  }





}


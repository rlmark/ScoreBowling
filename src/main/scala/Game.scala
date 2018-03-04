

class Game(players: Vector[Player] = Vector(new Player("1"), new Player("2"))) { // TODO: get elsewhere

  val board: Map[Player, Vector[FrameStatus]] = players.map(_ -> Vector()).toMap

  def play(players: Vector[Player]): Map[Player, Vector[FrameStatus]] = {
    for (i <- 1 to 10) {
      val test: Seq[Map[Player, Vector[FrameStatus]]] = players.map { player =>
        val playerSpecificFrames = board(player)
        val turnTaken = player.takeTurn(i)
        val currentFrame = Frame(turnTaken)
        val currentFrameStatus = FrameStatus(currentFrame)
        val updatedPreviousFrames = updateBoard(turnTaken, playerSpecificFrames)
        val rewriteBoard: Vector[FrameStatus] = currentFrameStatus +: updatedPreviousFrames
        board + (player -> rewriteBoard)
      }
    }
    board
  }

  // update frame method, raw list comes in, it calculatesTheScore and appends it to the FrameStatusList.
  def updateBoard(turn: List[Int], playerFrameStatuses: Vector[FrameStatus]) = {
      playerFrameStatuses.map {
        case Pending(frameToUpdate) => FrameStatus(Frame.update(turn, frameToUpdate))
        case finished@Score(_, _) => finished
      }
  }





}


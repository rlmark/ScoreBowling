

class Game(players: Vector[Player] = Vector(new Player("1"), new Player("2"))) { // TODO: get elsewhere

  val board: Map[Player, Vector[FrameStatus]] = players.map( _ -> Vector()).toMap
// maybe put frame inside of player. board needs more information, like the calculated values
  def play(players: Vector[Player] ) = {
    var currentFrameIndex = 0
    while (currentFrameIndex < 10) {
      players.map { player =>
        val turnTaken = player.takeTurn(currentFrameIndex)
        val currentFrame = Frame(turnTaken)
        // update board status given current Frame
        val playerSpecificFrames = board(player)
        currentFrame +: playerSpecificFrames// add the updatedFrames to that player's frame list
      }
      currentFrameIndex += 1
    }
  }






  // new turn comes in, creates a frame, also looks at existing list to see if it can update any non-complete


}


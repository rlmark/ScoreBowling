

class Game() {
  val players: Vector[Player] = Vector(new Player("1"), new Player("2")) // TODO: get from elsewhere

  val board: Map[Player, Vector[Frame]] = players.map( _ -> Vector()).toMap
// maybe put frame inside of player. board needs more information, like the calculated values
  def play(players: Vector[Player] ) = {
    var currentFrame = 0
    while (currentFrame < 10) {
      players.map{ player =>
        val turnTaken = player.takeTurn()
        turnTaken +: board(player)// add the frame to that player's frame list
      }
      currentFrame += 1
    }
  }

// Need a method to create a frame from a turn
  val testTurn1 = StandardTurn(4,Some(5))
  val testTurnSpare = StandardTurn(4,Some(6))
  val testTurnStrike = StandardTurn(10,None)
  val lastTurn = LastTurn(10, Some(10), Some(10))

  // new turn comes in, creates a frame, also looks at existing list to see if it can update any non-complete


}


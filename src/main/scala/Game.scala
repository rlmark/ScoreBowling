

class Game() {
  // TODO: Game mechanic for letting players take turns.
  // TODO: Function which adds frames until 10 frames are done to represent game play

  val players: Vector[Player] = Vector(new Player("1"), new Player("2")) // TODO: get from elsewhere
  // TODO: Game mechanic for adding rolls to a frame.
  val board: Map[Player, Vector[Frame]] = players.map( _ -> Vector()).toMap
// maybe put frame inside of player. board needs more information, like the calculated values
  def play(players: Vector[Player] ) = {
    var currentFrame = 0
    while (currentFrame < 10) {
      players.map{ player =>
        val createdFrame = Frame(player.takeTurn())
        createdFrame +: board(player)// add the frame to that player's frame list
      }
      currentFrame += 1
    }
  }

  def shouldIScore(newFrame: Frame, currentIndex: Int, existingFrames: Vector[Frame])= {
    // add the new frame to the existing frames
    // look at the last 3, if they are Strikes calculate
    // look at the last 2, if the first is a Spare, calculate
    // look at the last 2, if they are Strike, anything else, calculate
    ???
  }

  // Instead of looking forward, look backwards!!!
  // Build up a map while playing.
  // Note: If I use a stream of Frames, this maybe is less of an issue
  def calculateSingleFrameScore(frames: Vector[Frame], currentFrame: Int ): Int = {
    val frameToScore: Frame = frames(currentFrame)
    frameToScore match {
      case OpenFrame(rolls) => rolls.first + rolls.second.getOrElse(0)
      case Spare(_) => 10 + frames(currentFrame + 1).rolls.first
      case Strike(_) =>
        val nextTurn = frames(currentFrame + 1).rolls
        10 + nextTurn.first + nextTurn.second.getOrElse(frames(currentFrame + 2).rolls.first)// todo: Index out of bound error / last frame logic
    }
  }
}


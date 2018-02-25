class Game {

  // TODO: Function which adds frames until 10 frames are up to represent game play
  val frames : Vector[Frame] = ???

  // todo, rethink this as a recursive function?
  def calculateSingleFrameScore(frames: Vector[Frame], currentFrame: Int ): Int = {
    val frameToScore: Frame = frames(currentFrame)
    frameToScore match {
      case OpenFrame(rolls) => rolls.first + rolls.second.getOrElse(0) // Think about modeling this better as an Open Frame will always have a second roll value
      case Spare(_) => 10 + frames(currentFrame + 1).rolls.first
      case Strike(_) =>
        val nextTurn = frames(currentFrame + 1).rolls
        10 + nextTurn.first + nextTurn.second.getOrElse(frames(currentFrame + 2).rolls.first)// todo: Index out of bound error / last frame logic
    }
  }
}


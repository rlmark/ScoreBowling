import org.scalacheck.Gen

object BowlingGenerators {
  val samplePlayer: Gen[Player] = for {
    name <- Gen.alphaStr
  } yield new Player(name)

  val samplePlayers: Gen[Vector[Player]] = for {
    numberOfPlayers <- Gen.oneOf(1 to 4)
    players <- Gen.listOfN[Player](numberOfPlayers, samplePlayer)
  } yield players.toVector
}

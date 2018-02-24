sealed trait Frame {
  val rolls: Rolls
}
case class OpenFrame(rolls: Rolls)
case class Spare(rolls: Rolls)
case class Strike(rolls: Rolls)

case class Rolls(first: Int, second: Option[Int]) {

  // The third roll is only relevant for the last (10th) frame if you get a Strike
  val thirdRoll: Option[Int] = None
}


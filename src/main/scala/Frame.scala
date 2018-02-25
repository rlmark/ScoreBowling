sealed trait Frame {
  // maybe needs an index?
  val rolls: Rolls
}
case class OpenFrame(rolls: Rolls) extends Frame
case class Spare(rolls: Rolls) extends Frame
case class Strike(rolls: Rolls) extends Frame

object Frame {
  def apply(rolls: Rolls):Frame = {
    rolls match {
      case r@Rolls(first, Some(second)) if first + second < 10 => OpenFrame(r)
      case r@Rolls(first, Some(second)) if first + second == 10 => Spare(r)
      case r@Rolls(first, _ ) if first == 10 => Strike(r)
    }
  }
}

case class Rolls(first: Int, second: Option[Int]) {

  // The third roll is only relevant for the last (10th) frame if you get a Strike
  val thirdRoll: Option[Int] = None
}


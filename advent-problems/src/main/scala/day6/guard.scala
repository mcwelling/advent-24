package day6

class guard(var position: (Int, Int), var orientation: Int) {

  def rotate(): Unit = {
    this.orientation += 270
    if(this.orientation >= 360) {
      this.orientation %= 360
    }

  }

  def getNextCoordinates: (Int, Int) = {
    this.orientation match {
      case 0 => (this.position._1, this.position._2 + 1)
      case 90 => (this.position._1 - 1, this.position._2)
      case 180 => (this.position._1, this.position._2 - 1)
      case 270 => (this.position._1 + 1, this.position._2)
    }
  }

  def setPosition(newPosition: (Int, Int)): Unit = {
    this.position = newPosition
  }

  def translateOrientation: String ={
    this.orientation match {
      case 0 => ">"
      case 90 => "^"
      case 180 => "<"
      case 270 => "v"
    }
  }

}

object Main {
  def main(args: Array[String]): Unit = {
    val fileName = "Day-1-Historian-Hysteria/src/main/scala/input.txt"
    val problem1 = new p1(fileName)
    val problem2 = new p2(fileName)
    problem1.processFile()
    problem2.processFile()
  }
}
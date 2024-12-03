import day1.{d1p1, d1p2}
import day2.{d2p1, d2p2}
import day3.{d3p1, d3p2}

object Main {
  def main(args: Array[String]): Unit = {
    val fileNameDay1 = "advent-problems/src/main/scala/day1/input.txt"
    val fileNameDay2 = "advent-problems/src/main/scala/day2/input.txt"
    val fileNameDay3 = "advent-problems/src/main/scala/day3/input.txt"
    val day1p1 = new d1p1(fileNameDay1)
    val day1p2 = new d1p2(fileNameDay1)
    val day2p1 = new d2p1(fileNameDay2)
    val day2p2 = new d2p2(fileNameDay2)
    val day3p1 = new d3p1(fileNameDay3)
    val day3p2 = new d3p2(fileNameDay3)

    day1p1.processFile()
    day1p2.processFile()
    day2p1.processFile()
    day2p2.processFile()
    day3p1.processFile()
    day3p2.processFile()
  }
}
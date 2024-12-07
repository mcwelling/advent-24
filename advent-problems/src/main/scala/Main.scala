import day1.{d1p1, d1p2}
import day2.{d2p1, d2p2}
import day3.{d3p1, d3p2}
import day4.{d4p1, d4p2}
import day5.{d5p1, d5p2}

object Main {
  def main(args: Array[String]): Unit = {
    val fileNameDay1 = "advent-problems/src/main/scala/day1/input.txt"
    val fileNameDay2 = "advent-problems/src/main/scala/day2/input.txt"
    val fileNameDay3 = "advent-problems/src/main/scala/day3/input.txt"
    val fileNameDay4 = "advent-problems/src/main/scala/day4/input.txt"
    val fileNameDay5 = "advent-problems/src/main/scala/day5/input.txt"
    val day1p1 = new d1p1(fileNameDay1)
    val day1p2 = new d1p2(fileNameDay1)
    val day2p1 = new d2p1(fileNameDay2)
    val day2p2 = new d2p2(fileNameDay2)
    val day3p1 = new d3p1(fileNameDay3)
    val day3p2 = new d3p2(fileNameDay3)
    val day4p1 = new d4p1(fileNameDay4)
    val day4p2 = new d4p2(fileNameDay4)
    val day5p1 = new d5p1(fileNameDay5)
    val day5p2 = new d5p2(fileNameDay5)


    day1p1.processFile()
    day1p2.processFile()
    day2p1.processFile()
    day2p2.processFile()
    day3p1.processFile()
    day3p2.processFile()
    day4p1.processFile()
    day4p2.processFile()
    day5p1.processFile()
    day5p2.processFile()
  }
}
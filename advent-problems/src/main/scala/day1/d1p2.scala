package day1

import scala.collection.mutable
import scala.io.{BufferedSource, Source}

class d1p2(val fileName: String) {
  private val leftList = mutable.ListBuffer[Int]()
  private val rightFrequency = mutable.HashMap[Int, Int]()

  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)

    for (line <- bufferedSource.getLines()) {
      val row = line.trim.split("\\s+")
      leftList += row(0).toInt
      rightFrequency.updateWith(row(1).toInt) {
        case Some(freq) => Some(freq + 1)
        case None => Some(1)
      }
    }
    bufferedSource.close()

    println(calculateSimilarity(leftList, rightFrequency))
  }

  def calculateSimilarity(left: mutable.ListBuffer[Int], right: mutable.HashMap[Int, Int]): Int = {
    var similarityScore = 0
    left.foreach( item => {
      similarityScore += item * right.getOrElse(item, 0)
    })
    similarityScore
  }
}

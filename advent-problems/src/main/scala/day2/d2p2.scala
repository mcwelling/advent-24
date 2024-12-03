package day2

import scala.io.{BufferedSource, Source}

class d2p2(val fileName: String) {
  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)
    var safeReports = 0
    for (line <- bufferedSource.getLines()) {
      val row = line.trim.split("\\s+")
      safeReports += slidingWindowEval(row)
    }
    bufferedSource.close()
    println(safeReports)
  }

  def slidingWindowEval(row: Array[String]): Int = {
    val ascending = row(0).toInt > row(1).toInt // edge case where first two elem are eq
    val comparisons = row.sliding(2,1)
    var mistakes = 0
    val tolerance = 1
    for (elem <- comparisons) {
      val diff = elem(0).toInt - elem(1).toInt
      if (math.abs(diff) > 3 || math.abs(diff) < 1 || diff > 0 != ascending) mistakes += 1
    }
    if (mistakes > tolerance) 0 else 1
  }
}

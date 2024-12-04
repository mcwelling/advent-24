package day4

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Set}
import scala.io.{BufferedSource, Source}

/**
 * 140x140 input matrix evaluated by 3x3 sliding window
 * window diagonals are converted to sequences
 * sequences compared against 2 targets
 * if both diagonal sequences match, we have an X-MAS
 * X-MAS matches stored in set -- unnecessary, but used in previous prob to de-dup
 *
 * @param fileName : input file
 */
class d4p2(val fileName: String) {

  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)
    val matrix = ArrayBuffer[Array[String]]()
    for (line <- bufferedSource.getLines()) {
      matrix.append(line.split(""))
    }
    bufferedSource.close()

    val matches = findMatches(matrix)
    println(matches.size)
  }

  private def findMatches(matrix: ArrayBuffer[Array[String]]): mutable.Set[XInstanceCoordinates] = {
    val targetSequences = Seq(Seq("M", "A", "S"), Seq("S", "A", "M"))
    val matches = mutable.Set[XInstanceCoordinates]()

    // diags can all be expressed as sequences
    def checkSequence(seq: Seq[String]): Boolean = {
      targetSequences.contains(seq)
    }

    // move upper left corner of window s.t. coverage is complete
    for (row <- 0 to matrix.length - 3; col <- 0 to matrix(0).length - 3) {

      // Extract the current 3x3 window in focus
      val window = Array.tabulate(3, 3)((i, j) => matrix(row + i)(col + j))

      // Check diagonals of current window
      val diag1Seq = (0 until 3).map(i => window(i)(i))
      val diag1Coords = (0 until 3).map(i => (row + i, col + i))
      val diag1valid = checkSequence(diag1Seq)

      val diag2Seq = (0 until 3).map(i => window(i)(2 - i))
      val diag2Coords = (0 until 3).map(i => (row + i, col + 2 - i))
      val diag2valid = checkSequence(diag2Seq)

      // if diagonals match, append coordinates to our set
      if(diag1valid && diag2valid) {
        matches += XInstanceCoordinates(diag1Coords(1), diag1Coords(0), diag2Coords(0), diag2Coords(2), diag1Coords(2))
      }
    }

    matches
  }

}

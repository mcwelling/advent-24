package day4

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Set}
import scala.io.{BufferedSource, Source}

/**
 * 140x140 input matrix evaluated by 4x4 sliding window
 * window rows, cols, diags are all converted to sequences
 * sequences compared against 2 targets
 * matches stored in set (no need to de-dupe -- yes, this is lazy)
 *
 * SUBOPTIMAL as lots of redundant row/col comparisons
 * @param fileName : input file
 */
//
// class for tracking detected instance of word XMAS (complete)
// instances are read into set (no need to de-dupe)
// window advances L -> R, then Down 1 row and repeat
// after first col, only look at new col
// after first row, only look at new row


class d4p1(val fileName: String) {

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

  // Key Observation: rows, cols, diags can all be expressed as sequences
  private def findMatches(matrix: ArrayBuffer[Array[String]]): mutable.Set[InstanceCoordinates] = {
    val targetSequences = Seq(Seq("X", "M", "A", "S"), Seq("S", "A", "M", "X"))
    val matches = mutable.Set[InstanceCoordinates]()

    // compare sequence and append to our set
    def checkSequence(seq: Seq[String], coords: Seq[(Int, Int)]): Unit = {
      if (targetSequences.contains(seq)) {
        matches += InstanceCoordinates(coords.head, coords(1), coords(2), coords(3))
      }
    }

    // move upper left corner of window s.t. coverage is complete
    for (row <- 0 to matrix.length - 4; col <- 0 to matrix(0).length - 4) {
      // Extract the current 4x4 window in focus
      val window = Array.tabulate(4, 4)((i, j) => matrix(row + i)(col + j))

      // Check rows of current window
      for (i <- 0 until 4) {
        val seq = window(i)
        val coords = (0 until 4).map(j => (row + i, col + j))
        checkSequence(seq, coords)
      }

      // Check columns of current window
      for (j <- 0 until 4) {
        val seq = (0 until 4).map(i => window(i)(j))
        val coords = (0 until 4).map(i => (row + i, col + j))
        checkSequence(seq, coords)
      }

      // Check diagonals of current window
      val diag1Seq = (0 until 4).map(i => window(i)(i))
      val diag1Coords = (0 until 4).map(i => (row + i, col + i))
      checkSequence(diag1Seq, diag1Coords)

      val diag2Seq = (0 until 4).map(i => window(i)(3 - i))
      val diag2Coords = (0 until 4).map(i => (row + i, col + 3 - i))
      checkSequence(diag2Seq, diag2Coords)
    }

    matches
  }
}

package day3

import scala.io.{BufferedSource, Source}

class d3p2(val fileName: String) {

  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)
    val expressionDetectionRegex = "(mul\\(\\d+,\\d+\\)|don't\\(\\)|do\\(\\))".r
    val numPairDetectionRegex = "(\\d+,\\d+)".r
    var total = 0
    var include = true
    expressionDetectionRegex.findAllIn(bufferedSource.mkString)
      .filter {
        case "don't()" =>
          include = false // Skip items after instance of "don't()"
          false           // Exclude "don't()" itself
        case "do()" =>
          include = true  // Resume including items after instance of "do()"
          false           // Exclude "do()" itself
        case _ => include // Include or exclude based on current inclusion state
      }
      .map { mulExp => numPairDetectionRegex.findFirstIn(mulExp).getOrElse(() => "").toString }
      .map { numPairAsString => numPairAsString.split(",") }
      .foreach { numStringArray => total += (numStringArray(0).toInt * numStringArray(1).toInt) }
    println(total)
  }



}

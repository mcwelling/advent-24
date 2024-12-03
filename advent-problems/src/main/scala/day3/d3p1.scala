package day3

import scala.io.{BufferedSource, Source}

class d3p1(val fileName: String) {

  private val terminatingChars = List(")")

  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)
    val mulExpDetectionRegex = "(mul\\(\\d+,\\d+\\))".r
    val numPairDetectionRegex = "(\\d+,\\d+)".r
    var total = 0
    mulExpDetectionRegex.findAllIn(bufferedSource.mkString)
      .map { mulExp => numPairDetectionRegex.findFirstIn(mulExp).getOrElse(() => "").toString }
      .map { numPairAsString => numPairAsString.split(",") }
      .foreach { numStringArray => total += (numStringArray(0).toInt * numStringArray(1).toInt) }
    println(total)
  }



}

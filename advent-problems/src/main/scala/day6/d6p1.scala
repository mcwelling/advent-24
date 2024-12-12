package day6

import scala.io.Source
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class d6p1 (val fileName: String){

  def processFile(): Unit = {
    val bufferedSource = Source.fromFile(fileName)
    val grid = ArrayBuffer[ArrayBuffer[String]]()
    for (line <- bufferedSource.getLines()) {
      grid.addOne(line.split("").to(ArrayBuffer))
    }

  val arena = new matrix(grid)
  print(arena.evaluate())
  }

}



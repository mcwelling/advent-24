package day1

import scala.collection.mutable
import scala.io.{BufferedSource, Source}

class d1p1(val fileName: String) {
  private val heap1 = mutable.PriorityQueue[Int]()
  private val heap2 = mutable.PriorityQueue[Int]()

  def processFile(): Unit = {
    val bufferedSource: BufferedSource = Source.fromFile(this.fileName)

    for (line <- bufferedSource.getLines()) {
      val row = line.trim.split("\\s+")
      heap1.enqueue(row(0).toInt)
      heap2.enqueue(row(1).toInt)
    }
    bufferedSource.close()
    println(getHeapDiff(heap1, heap2))
  }

  def getHeapDiff(h1: mutable.PriorityQueue[Int], h2: mutable.PriorityQueue[Int]): Int = {
    var diff = 0
    while (h1.nonEmpty && h2.nonEmpty) {
      val item1 = h1.dequeue()
      val item2 = h2.dequeue()
      diff += math.abs(item1 - item2)
    }
    diff
  }

}

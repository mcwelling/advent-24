package day5

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source


/**
 * spent a day learning topological sort and ended up implementing this bs instead... fml
 * @param fileName - input file
 */
class d5p1(val fileName: String) {

  def processFile(): Unit = {
    val bufferedSource = Source.fromFile(fileName)
    val ruleType = "(\\d+\\|\\d+)".r

    //graph
    val rules = mutable.HashMap[Int, mutable.Set[Int]]()
    val inDegree = mutable.HashMap[Int, Int]().withDefaultValue(0)
    val runs = ArrayBuffer[Array[Int]]()
    for (line <- bufferedSource.getLines()) {
      line match {
        case ruleType(line) =>
          val rule = line.split("\\|")
          rules.getOrElseUpdate(rule(1).toInt, mutable.Set[Int]()) += rule(0).toInt
          inDegree(rule(1).toInt) += 1
          inDegree.getOrElseUpdate(rule(0).toInt, 0)
        case _ =>
          if (!line.isBlank) {
            val run = line.split(",")
            runs.addOne(run.map(item => item.toInt))
          }
      }
    }

    def evaluateRun(run: Array[Int]): Boolean = {
      val satisfied = mutable.Set[Int]()
      for (node <- run) {
        val required = rules.getOrElse(node, Set[Int]()).filter { item => run.contains(item)}
        if (!required.subsetOf(satisfied)) {
          return false
        }
        satisfied += node
      }
      true // All nodes in the input satisfy the constraints
    }

    val validRuns = runs.filter { run => evaluateRun(run) }
    println(validRuns.map { item => item(item.length / 2) }.sum)
  }

}

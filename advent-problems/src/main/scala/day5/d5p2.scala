package day5

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source


/**
 * a cleverer man than i would not have resorted to recursion, alas...
 * @param fileName - input file
 */
class d5p2(val fileName: String) {

  def processFile(): Unit = {
    val bufferedSource = Source.fromFile(fileName)
    val ruleType = "(\\d+\\|\\d+)".r

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

    // pull reqs set from hashmap, filter down to relevant deps using run items
    def getRequirements(node: Int, run: Array[Int]): collection.Set[Int] = {
      rules.getOrElse(node, Set[Int]()).filter { run.contains }
    }

    // true if ordered correctly. false if out of order
    def evaluateRun(run: Array[Int]): Boolean = {
      val satisfied = mutable.Set[Int]()
      for (node <- run) {
        val required = getRequirements(node, run)
        if (!required.subsetOf(satisfied)) {
          return false
        }
        satisfied += node
      }
      true
    }

    @tailrec
    def fixRun(run: Array[Int]): Array[Int] = {
      val result = mutable.ArrayBuffer[Int]()
      val satisfied = mutable.Set[Int]()

      for (node <- run) {
        val required = getRequirements(node, run)
        if (!required.subsetOf(satisfied)) {
          // Insert all missing dependencies before the current node -- i know... it gets worse
          val missingDependencies = required.diff(satisfied)
          for (dep <- missingDependencies) {
            if (!result.contains(dep)) {
              result += dep
              satisfied += dep
            }
          }
        }
        if (!result.contains(node)) {
          result += node
          satisfied += node
        }
      }
      // keep prepending the prereqs until it works 4head
      if(evaluateRun(result.toArray)){
        result.toArray
      } else {
        fixRun(result.toArray)
      }
    }

    val fixedRuns = ArrayBuffer[Array[Int]]()

    for (run <- runs) {
      if (!evaluateRun(run)) {
        fixedRuns.addOne(fixRun(run))
      }
    }

    println(fixedRuns.map { item => item(item.length / 2) }.sum)
  }
}

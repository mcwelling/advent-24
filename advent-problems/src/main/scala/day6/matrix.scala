package day6

import day6.Terrain.Terrain

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

class matrix(var geo: ArrayBuffer[ArrayBuffer[String]]) {
  private val height = this.geo.length
  private val width = this.geo.head.length
  private val guard = new guard(getGuardPosition, getGuardOrientation)

  def evaluate(): Int = {
    moveGuard()
    // uncomment for the terrain picture
    // geo.foreach { item => println(item.mkString(",")) }
    getVisitedIdxCount
  }

  private def markVisited(coordinates: (Int, Int)): Unit = {
    this.geo(coordinates._1).update(coordinates._2, "@")
  }

  private def markGuard(coordinates: (Int, Int)): Unit = {
    this.geo(coordinates._1).update(coordinates._2, guard.translateOrientation)
  }

  @tailrec
  private def moveGuard(): Unit = {
    val newCoords = this.guard.getNextCoordinates
    parseTerrain(newCoords) match {
      case Terrain.Normal =>
        markVisited(this.guard.position)
        markGuard(newCoords)
        this.guard.setPosition(newCoords)
        moveGuard()
      case Terrain.Obstruction =>
        this.guard.rotate()
        moveGuard()
      case Terrain.Exit =>
        markVisited(this.guard.position)
    }
  }

  private def parseTerrain(coordinates: (Int, Int)): Terrain = {
    if (coordinates._1 < 0 || coordinates._1 >= height || coordinates._2 < 0 || coordinates._2 >= width) {
      return Terrain.Exit
    }
    this.geo(coordinates._1)(coordinates._2) match {
      case "." => Terrain.Normal
      case "@" => Terrain.Normal
      case "#" => Terrain.Obstruction
      case "^" | ">" | "<" | "v" => Terrain.Guard
      case _ => Terrain.Exit
    }
  }

  private def parseGuardOrientation(coordinates: (Int, Int)): Int = {
    this.geo(coordinates._1)(coordinates._2) match {
      case ">" => 0
      case "^" => 90
      case "<" => 180
      case "v" => 270
    }
  }

  private def getGuardPosition: (Int, Int) = {
    for (row <- 0 to geo.length; col <- 0 to geo.head.length) {
      if(parseTerrain(row, col) == Terrain.Guard) {
        return (row, col)
      }
    }
    (-1,-1)
  }

  private def getGuardOrientation: Int = {
    val position = getGuardPosition
    parseGuardOrientation(position)
  }

  private def getVisitedIdxCount: Int = {
    var visited = 0
    for (row <- this.geo.indices; col <- this.geo.head.indices) {
      if(this.geo(row)(col) == "@") {
        visited += 1
      }
    }
    visited
  }

}

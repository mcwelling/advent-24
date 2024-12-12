package day6

object Terrain extends Enumeration {
  type Terrain = Value

  val Normal, Obstruction, Exit, Guard = Value
}
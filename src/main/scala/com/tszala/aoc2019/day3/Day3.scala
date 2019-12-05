package com.tszala.aoc2019.day3

import com.tszala.aoc2019.utils.LinesReader

object Day3 {
  def main(args: Array[String]):Unit = {
    val filename = "src\\main\\scala\\com\\tszala\\aoc2019\\day3\\input.txt"
    val lines = LinesReader.readLinesOrExit(filename = filename)
    val wireOne = lines.head.split(",").toList
    val wireTwo = lines.tail.head.split(",").toList
    println(s"WireOne: ${wireOne}")
    println(s"WireTwo: ${wireTwo}")
    val centralPort = (0,0)

    val coordinatesForWireOne = calculateCoordinatesForWire((0, 0), wireOne, List())
    val coordinatesForWireTwo = calculateCoordinatesForWire((0, 0), wireTwo, List())

    val crossingPoints = coordinatesForWireOne.toSet.intersect(coordinatesForWireTwo.toSet).toList
    val lowestDistance = crossingPoints.map(p => manhattanDistance(centralPort, p)).sorted.head
    println(crossingPoints)
    println(lowestDistance)

    val minimalSteps = crossingPoints.map(p=>coordinatesForWireOne.indexOf(p) + coordinatesForWireTwo.indexOf(p)).sorted.head
    println(s"Minimal steps: ${minimalSteps + 2}")
  }

  type Point = (Int,Int)

  def manhattanDistance(p1:(Int, Int), p2: (Int, Int)) = Math.abs(p1._1 - p2._1) + Math.abs(p1._2 - p2._2)

  def calculateCoordinatesForWire(p:(Int,Int), codes:List[String], result: List[(Int,Int)]):List[(Int,Int)] = {
    codes match {
      case Nil => result
      case h::t => val coordinates = getCoordinates(p,h); calculateCoordinatesForWire(coordinates.last,t,result:::coordinates)
    }
  }

  def getCoordinates(point: (Int,Int), code: String): List[(Int,Int)] = {
    val number = code.substring(1).toInt
    val l = code(0) match {
      case 'R' => goRight((point._1, point._2), number)
      case 'L' => goLeft((point._1, point._2), number)
      case 'U' => goUp((point._1, point._2), number)
      case 'D' => goDown((point._1, point._2), number)
    }
    l
  }

  def goUp(point: Tuple2[Int,Int], steps : Int) = Range.inclusive(1, steps).map(n => (point._1,point._2 + n)).toList
  def goDown(point: Tuple2[Int,Int], steps : Int) = Range.inclusive(1, steps).map(n => (point._1,point._2 - n)).toList
  def goLeft(point: Tuple2[Int,Int], steps : Int) = Range.inclusive(1, steps).map(n => (point._1 - n,point._2)).toList
  def goRight(point: Tuple2[Int,Int], steps : Int) = Range.inclusive(1, steps).map(n => (point._1 + n,point._2)).toList


}

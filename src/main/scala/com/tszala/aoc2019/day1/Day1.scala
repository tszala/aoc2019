package com.tszala.aoc2019.day1

import com.tszala.aoc2019.utils.LinesReader
import scala.util.{Failure, Success}

object Day1 {
  def main(args: Array[String]) = {
    val filename = "src\\main\\scala\\com\\tszala\\aoc2019\\day1\\input.txt"
    val modulesMass:List[Int] = LinesReader.readTextFileWithTry(filename) match {
      case Success(l) => l.map(_.toInt)
      case Failure(e) => println(e)
        System.exit(1)
        List.empty
    }

    val fuelForModules = modulesMass.map(m=>m/3).map(m=>m-2).sum

    val fuelTotal = modulesMass.map(m=>m/3).map(m=>m-2).map(m=>m+sumAdditionalFuel(m,0)).sum
    println(s"The fuel for modules is ${fuelForModules}, the total fuel is ${fuelTotal}")
  }

  def sumAdditionalFuel(fuel: Int, acc : Int) : Int = {
    val newFuel = (fuel / 3)
    val newFuel2 = newFuel - 2
    if(newFuel2 <= 0)
      acc
    else
      sumAdditionalFuel(newFuel2, newFuel2 + acc)
  }

}

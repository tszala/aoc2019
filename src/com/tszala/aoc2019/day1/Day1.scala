package com.tszala.aoc2019.day1

import com.tszala.aoc2019.utils.LinesReader
import scala.util.{Failure, Success}

object Day1 {
  def main(args: Array[String]) = {
    val filename = "src\\com\\tszala\\aoc2019\\day1\\input.txt"
    val modulesMass:List[Int] = LinesReader.readTextFileWithTry(filename) match {
      case Success(l) => l.map(_.toInt)
      case Failure(e) => println(e)
        System.exit(1)
        List.empty
    }

    val sum = modulesMass.map(m=>m/3).map(m=>m-2).sum
    println(s"The sum is ${sum}")
  }
}

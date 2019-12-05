package com.tszala.aoc2019.utils

import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.{Failure, Success, Try}

object LinesReader {

  def readTextFileWithTry(f:String) :Try[List[String]] = {
    Try {
      val lines = LoanObject.using(Source.fromFile(f)) { source =>
        (for (line <- source.getLines) yield line).toList
      }
      lines
    }
  }

  def readLinesOrExit(filename: String): List[String] = {
    val inputs: List[String] = LinesReader.readTextFileWithTry(filename) match {
      case Success(l) => l
      case Failure(e) => println(e)
        System.exit(1)
        List.empty
    }
    inputs
  }

  def readFirstLineAsCommaSeparatedInts(filename: String): ArrayBuffer[Int] = {
    val instructionLines: List[String] = LinesReader.readLinesOrExit(filename)
    ArrayBuffer.from(instructionLines.head.split(",").map(_.toInt))
  }

}

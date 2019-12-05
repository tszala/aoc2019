package com.tszala.aoc2019.day2

import com.tszala.aoc2019.utils.LinesReader

import scala.collection.mutable.ArrayBuffer

object Day2 {
  def main(args: Array[String]):Unit = {
    val filename = "src\\main\\scala\\com\\tszala\\aoc2019\\day2\\input.txt"
    //val instructions = ArrayBuffer.from(List(1,9,10,3,2,3,11,0,99,30,40,50))//readInstructions(filename)
    val instructions = readInstructions(filename)

    val code = step(0, addVerbAndNoun(12,2, instructions))
    println(s"Output is ${code}")

    val pairs = for(i <- 0 until 100;j<- 0 until 100) yield (i,j)
    val pair = pairs.find(v => step(0, addVerbAndNoun(v._1, v._2, instructions)) == 19690720)

    println(s"Quiz answer is ${100*pair.get._1 + pair.get._2}")

  }

  def addVerbAndNoun(noun: Int, verb : Int, program: ArrayBuffer[Int]) = {
    val newProgram = ArrayBuffer.from(program)
    newProgram(1) = noun
    newProgram(2) = verb
    newProgram
  }

  def add = (a: Int, b: Int) => a + b

  def multiply = (a: Int, b: Int) => a * b

  def step(pointer: Int, stack: ArrayBuffer[Int]): Int = {
    val opcode = stack(pointer)
    opcode match {
      case 1 => op(add, pointer, stack)
      case 2 => op(multiply, pointer, stack)
      case 99 => stack(0)
      case _ => throw new Exception(s"Unknown instruction code ${opcode}")
    }
    if(opcode == 99) stack(0) else step(pointer + 4, stack)
  }

  def op(o: (Int, Int) => Int, address: Int, stack: ArrayBuffer[Int]) = stack(stack(address + 3)) = o(stack(stack(address + 1)), stack(stack(address + 2)))

  def readInstructions(filename: String): ArrayBuffer[Int] = {
    val instructionLines: List[String] = LinesReader.readLinesOrExit(filename)
    ArrayBuffer.from(instructionLines.head.split(",").map(_.toInt))
  }

}

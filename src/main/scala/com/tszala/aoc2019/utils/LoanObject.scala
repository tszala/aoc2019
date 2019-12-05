package com.tszala.aoc2019.utils

object LoanObject {
    def using[A <: {def close(): Unit}, B](resource: A)(f: A=>B): B =
      try {
        f(resource)
      } finally {
        resource.close()
      }
}
